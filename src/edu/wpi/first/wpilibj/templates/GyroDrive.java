//        /*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.wpi.first.wpilibj.templates;
//
//import edu.wpi.first.wpilibj.DriverStationLCD;
//
//
///**
// *
// * @author Freshman
// */
//public class GyroDrive extends ScibotThread {
//      
////  //Constant variables for ANGLE
//    public static final double ANGLE = 45;
//    public static final double BUFFER = 5;
//    public static final double SPEED = 0.5;

//    //Variables to be used in quick turning 
//    private boolean check = true; //Checks if robot is in motion (already turning)
//    private double startVal = 0; //Will be changed to the current gyro angle
//    private double dir = 0; //Direction in which the robot is going to turn
//    
//    //Starts everything
//    public void start() {
//        Hardware.gyro.reset(); //Resets gyro value to 0
//        super.start();
//    }
//    
//    public void function() {
//        
          //I'm assuming this has to do with the gyro station
//        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.gyro.getAngle())); //prints angle of gyro on driver station
//        Hardware.dLCD.updateLCD(); //constantly updates as values change...?
//        
//        double rightVal, leftVal;
//        
//        //Checks if gyro is already turning
//        if(check) {
//            if(Hardware.remote.getRawButton(11)) {
//                check = false; //turns check to false so that you can't repeatedly press the quick turning button
//                startVal = Hardware.gyro.getAngle(); //sets current gyro angle to startVal
//                dir = 1; //sets direction
//            }
//            else if(Hardware.remote.getRawButton(12)) {
//                check = false; //turns check to false so that you can't repeatedly press the quick turning button
//                startVal = Hardware.gyro.getAngle(); //sets current gyro angle to startVal
//                dir = -1; //sets direction
//            }
//        }
//        else {
//            if(Math.abs(Hardware.gyro.getAngle() - (startVal + ANGLE*dir)) > BUFFER) { 
//                if(Math.abs(Hardware.rightJoy.getY()) < 0.1 && Math.abs(Hardware.leftJoy.getY()) < 0.1) {
//                    Hardware.frontRightTalon.set(-SPEED * dir); //sets talon to the speed corrisponding to the button that was pressed
//                    Hardware.backRightTalon.set(-SPEED * dir); //same
//                    Hardware.frontLeftTalon.set(SPEED * dir); //same
//                    Hardware.frontLeftTalon.set(SPEED * dir); //same
//                }
//                else {
//                    check = false;
//                }
//            }
//            else {
//                check = false;
//            }
//        }
////        if (check) {
////
////            gyroState = Hardware.gyro.getAngle(); //gyrostate will equal to 0
////            if (rButton) {
////                check = false;
////                Hardware.frontLeftTalon.set(1);
////                Hardware.backLeftTalon.set(1);
////
////                Hardware.frontRightTalon.set(1);
////                Hardware.backRightTalon.set(1);
////
////            } else if (lButton) {
////                check = false;
////                Hardware.frontLeftTalon.set(-1);
////                Hardware.backLeftTalon.set(-1);
////
////                Hardware.frontRightTalon.set(rightVal);
////                Hardware.backRightTalon.set(rightVal);
////            }
////        }
////
////        if ((gyroState + 45 == Hardware.gyro.getAngle() || 360 + (gyroState - 45) == Hardware.gyro.getAngle()) && !check) {
////            Hardware.frontLeftTalon.set(0); //stops the turning once the robot rotates 45 degrees
////            Hardware.backLeftTalon.set(0); //same
////
////            Hardware.frontRightTalon.set(0); //same
////            Hardware.backRightTalon.set(0); //same
////            check = true;
////        }
//    }
//}
