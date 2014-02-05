package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.CriteriaCollection;

public class Vision extends ScibotThread {

    CriteriaCollection cc;
    static Boolean targetHot = null;
    

    public void start() {
        System.out.println("robotInit");
        Hardware.camera.writeExposurePriority(AxisCamera.ExposurePriorityT.frameRate);//NEED THIS
        Hardware.camera.writeColorLevel(100);
        Hardware.camera.writeBrightness(50);
        Hardware.camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        System.out.println("Camera");
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        System.out.println("CC");
        cc.addCriteria(MeasurementType.IMAQ_MT_AREA, 500, 6553, false);                //not actual values(from last year)
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_NUMBER_OF_HOLES, 1, 3, false); //not actual values(from last year)
        System.out.println("leave init");
        super.start();
    }
    
    public void function() {
        if(targetHot == null) {
            targetHot = (Boolean) hotDetector();
        }
        else {
            if(!targetHot) {
                Thread.sleep(5000); //Wait 5 seconds
                targetHot = true;
            }
            running = false; //Stops the thread
        }
    }

    public boolean hotDetector() {
        ColorImage image = null;
        BinaryImage thresholdImage = null;
        BinaryImage bigObjectsImage = null;
        BinaryImage convexHullImage = null;
        BinaryImage filteredImage = null;
        boolean hot = false;
        try {
            image = Hardware.camera.getImage();
            thresholdImage = image.thresholdRGB(0, 45, 25, 255, 0, 47);
            bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);
            convexHullImage = bigObjectsImage.convexHull(false);
            filteredImage = convexHullImage.particleFilter(cc);
            ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();
            if(reports.length + 1 > 0){
                hot = true;
            }
            
        } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        } finally {
        }
        try {
            filteredImage.free();
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        }
return hot;
    }
}
