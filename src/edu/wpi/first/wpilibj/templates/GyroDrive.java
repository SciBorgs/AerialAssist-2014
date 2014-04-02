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
////    //Constant variables for ANGLE
//    public static final double ANGLE = 45;
//    public static final double BUFFER = 5;
//    public static final double SPEED = 0.5;
////
//    private boolean check = true;
//    private double startVal = 0;
//    private double dir = 0;
//    
//    
//    public void start() {
//        Hardware.gyro.reset();
//        super.start();
//    }
//    
//    public void function() {
//
//        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.gyro.getAngle()));
//        Hardware.dLCD.updateLCD();
//        
//        double rightVal, leftVal;
//        
//        if(check) {
//            if(Hardware.remote.getRawButton(11)) {
//                check = false;
//                startVal = Hardware.gyro.getAngle();
//                dir = 1;
//            }
//            else if(Hardware.remote.getRawButton(12)) {
//                check = false;
//                startVal = Hardware.gyro.getAngle();
//                dir = -1;
//            }
//        }
//        else {
//            if(Math.abs(Hardware.gyro.getAngle() - (startVal + ANGLE*dir)) > BUFFER) {
//                if(Math.abs(Hardware.rightJoy.getY()) < 0.1 && Math.abs(Hardware.leftJoy.getY()) < 0.1) {
//                    Hardware.frontRightTalon.set(-SPEED * dir); 
//                    Hardware.backRightTalon.set(-SPEED * dir);
//                    Hardware.frontLeftTalon.set(SPEED * dir);
//                    Hardware.frontLeftTalon.set(SPEED * dir);
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
////            Hardware.frontLeftTalon.set(0);
////            Hardware.backLeftTalon.set(0);
////
////            Hardware.frontRightTalon.set(0);
////            Hardware.backRightTalon.set(0);
////            check = true;
////        }
//    }
//}
