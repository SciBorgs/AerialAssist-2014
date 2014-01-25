package SciBorgs;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.*;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVision.Rect;

public class VisionThread extends SimpleRobot {

    AxisCamera camera;
    CriteriaCollection cc;      // the criteria for doing the particle filter operation

    public class Targets {

        double rectangularity, aspectRatioInner, aspectRatioOuter, xEdge, yEdge;
    }

    public void robotInit() {
        System.out.println("robotInit");
        camera = AxisCamera.getInstance();  // get an instance of the camera
        camera.writeExposurePriority(AxisCamera.ExposurePriorityT.frameRate);//NEED THIS
        camera.writeColorLevel(100);
        camera.writeBrightness(50);
        camera.writeResolution(AxisCamera.ResolutionT.k640x480); //Needs to be corrected
        System.out.println("Camera");
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        System.out.println("CC");
        cc.addCriteria(MeasurementType.IMAQ_MT_AREA, 500, 6553, false);                //not actual values(from last year)
        cc.addCriteria(NIVision.MeasurementType.IMAQ_MT_NUMBER_OF_HOLES, 1, 3, false); //not actual values(from last year)
        System.out.println("leave init");
    }

    public void autonomous() {
        System.out.println("Autonomus started");
        while (isAutonomous() && isEnabled()) {
            try {
                ColorImage image = camera.getImage();     // comment if using stored image
                BinaryImage thresholdImage = image.thresholdHSV(0, 45, 25, 255, 0, 47);
                BinaryImage convexHullImage = thresholdImage.convexHull(false);          // fill in occluded rectangles
                BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // filter out small particles

                //iterate through each particle and score to see if it is a target
                Targets targets[] = new Targets[filteredImage.getNumberParticles()];
                System.out.println("Targets");
                if(targets.length > 0){
                    Timer.delay(4);
                    //put technical stuff here
                }
                filteredImage.free();
                convexHullImage.free();
                thresholdImage.free();
                image.free();

            } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
                ex.printStackTrace();
            } catch (NIVisionException ex) {
                ex.printStackTrace();
            }
        }
    }
//Not needed this year :P
//  
//    public void autonomousControl(ParticleAnalysisReport report) {
//        System.out.println("Operator control");
//    boolean isTarget;
//        while (isOperatorControl() && isEnabled()) {
//               if(report != null){
//            int leftDistance, rightDistance;
//            
//            leftDistance = report.boundingRectLeft;
//            rightDistance = report.imageWidth - report.boundingRectLeft - report.boundingRectWidth;
//            
//            //horizontal control
//            if(report.center_mass_x_normalized > 180){
//                lSpeed = -gear;
//                rSpeed = -gear;
//                isTarget = false;
//                System.out.println("Turn Spd -0.2");
//            }else if(report.center_mass_x_normalized < 140){
//                lSpeed = gear;
//                rSpeed = gear;
//                isTarget = false;
//                System.out.println("Turn Spd 0.2");
//            }else if(report.center_mass_x_normalized > 160 && report.center_mass_x_normalized < 180){
//                lSpeed = -gear/3;
//                rSpeed = -gear/3;
//                isTarget = false;
//                System.out.println("Turn Spd -0.1");
//            }else if(report.center_mass_x_normalized < 160 && report.center_mass_x_normalized > 140){
//                lSpeed = gear/3;
//                rSpeed = gear/3;
//                isTarget = false;
//                System.out.println("Turn Spd 0.1");
//            }else if(report.center_mass_x_normalized == 160){
//                lSpeed = 0;
//                rSpeed = 0;
//                isTarget = true;
//                System.out.println("Centered!    ");
//            }
//            
//        }else{
//            //what to do when nothing is in sight
//            System.out.println("I'm BLIND!     ");
//        }
//    }
//        }
//Computes the estimated distance THIA IS FOR LAST YEARS, FORMULA MAY COME IN HANDY
//    double computeDistance (BinaryImage image, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException {
//            double rectShort, height;
//            int targetHeight;
//
//            rectShort = NIVision.MeasureParticle(image.image, particleNumber, false, MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
//            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
//            //on skewed rectangles
//            height = Math.min(report.boundingRectHeight, rectShort);
//            targetHeight = outer ? 29 : 21;
//
//            return X_IMAGE_RES * targetHeight / (height * 12 * 2 * Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
//    }
}
