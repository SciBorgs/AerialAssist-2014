package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.CriteriaCollection;

public class Vision extends ScibotThread {

    CriteriaCollection cc;
    Boolean targetHot;
    
    
    public void start() {
        Hardware.camera.writeExposurePriority(AxisCamera.ExposurePriorityT.frameRate);//NEED THIS
        Hardware.camera.writeColorLevel(100);
        Hardware.camera.writeBrightness(50);
        Hardware.camera.writeResolution(AxisCamera.ResolutionT.k320x240);
        System.out.println("Camera");
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        System.out.println("CC");
        cc.addCriteria(MeasurementType.IMAQ_MT_RATIO_OF_EQUIVALENT_RECT_SIDES(5.7, (float)6,false);     //horizontal aspect ratio
        cc.addCriteria(MeasurementType.IMAQ_MT_RATIO_OF_EQUIVALENT_RECT_SIDES(0.075,0.175,false);
        System.out.println("leave init");
        super.start();
    }
    
    public void function() {
        if(targetHot == null) {
            targetHot = new Boolean(hotDetector());
        }
        else {
            if(!targetHot.booleanValue()) {
                try {
                    Hardware.dLCD.println(DriverStationLCD.Line.kUser5, 1, "Target isn't hot");
                    Thread.sleep(5000); //Wait 5 seconds
                } catch (InterruptedException e) {}
            }
            Hardware.dLCD.println(DriverStationLCD.Line.kUser5, 1, "Target is hot");
            targetHot = new Boolean(true);
            stop(); //Stops the thread
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
            thresholdImage = image.thresholdRGB(0, 103, 109, 255, 134, 255);
            bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);
            convexHullImage = bigObjectsImage.convexHull(false);
            filteredImage = convexHullImage.particleFilter(cc);
            ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();
            if(reports.length >= 1){
                hot = true;
            } 
            filteredImage.free();
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
        } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
            ex.printStackTrace();}
return hot;
    }
}
