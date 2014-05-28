//package edu.wpi.first.wpilibj.templates;
//
//import edu.wpi.first.wpilibj.DriverStationLCD;
//
//public class AutoUltrasonic extends ScibotThread {
//
//    double leftValue = Hardware.leftSensor.getRangeInches(); //sets the range (in inches) that is read by the left sensor to leftValue
//    double rightValue = Hardware.rightSensor.getRangeInches(); //sets the range (in inches) that is read by the right sensor to rightValue
//    double avgValue = (leftValue + rightValue) / 2; //Average of the two values (we just need the general distance from straight ahead)
//    final double BUFFER = 1;
//    double distance = 1;
//    boolean ready = false;
//    
//    //Overriden start method, resets ready to false to restart autonomous cycle
//    public void start() {
//        ready = false;
//        super.start();
//    }
//    
//    //Function that aligns and gets the robot to the proper distance, then 
//    //
//    public void function() {
//        if(!Vision.targetHot.booleanValue()) return; //Checks if target is hot. If it isn't, it returns and does nothing.
//        else {
//            Shooter.shoot(); //if target is hot, shoot
//            Shooter.withdraw(); //after shooting is finished, withdraw claw
//        }
//        if(!ready) {
//            while(Math.abs(leftValue - rightValue) > BUFFER) { //checks to see if leftValue and rightValue are not equal, and then realigns robot
//                align(); //realigns robot until leftValue and rightValue are equal
//            }
//            while(!ready) {
//                ready = travel(); 
//            }
//        }
//        else {
//            Shooter.shoot();
//            while(Math.abs(leftValue - rightValue) > BUFFER) {
//                align();
//            }
//            //Reassign distance to 2
//            distance = 2;
//            while(!ready) {
//                ready = travel();
//            }
//        }
//        stop();
//    }
//    
//    public void align() {
//        double speed = (leftValue - rightValue) * 0.1;
//        if (leftValue - rightValue > 0) { //this would be true if the robot is slanted so that the left side is closer to said object
//            //realigns robot so that robot is straight
//            Hardware.backLeftTalon.set(speed);
//            Hardware.frontLeftTalon.set(speed);
//            Hardware.frontRightTalon.set(-speed);
//            Hardware.backRightTalon.set(-speed);
//        } else if (rightValue - leftValue > 0) { //this would be true if the robot is slanted so that the right side is closer to said object
//            //realigns robot so that robot is straight
//            Hardware.backLeftTalon.set(-speed);
//            Hardware.frontLeftTalon.set(-speed);
//            Hardware.frontRightTalon.set(speed);
//            Hardware.backRightTalon.set(speed);
//        } else { //this would be true if robot is already straight and therefore robot would not need realignment 
//            Hardware.backLeftTalon.set(0);
//            Hardware.frontLeftTalon.set(0);
//            Hardware.frontRightTalon.set(0);
//            Hardware.backRightTalon.set(0);
//        }
//    }
//
//    public boolean travel() {
//        if(Math.abs(distance - avgValue) > BUFFER) {
//            return true;
//        }
//        else {
//            double speed = -((distance - avgValue) / distance);
//            if (distance - avgValue > 0) {
//                Hardware.backLeftTalon.set(speed);
//                Hardware.frontLeftTalon.set(speed);
//                Hardware.frontRightTalon.set(speed);
//                Hardware.backRightTalon.set(speed);
//            } else if (distance - avgValue < 0) {
//                Hardware.backLeftTalon.set(speed);
//                Hardware.frontLeftTalon.set(speed);
//                Hardware.frontRightTalon.set(speed);
//                Hardware.backRightTalon.set(speed);
//            } else {
//                Hardware.backLeftTalon.set(0);
//                Hardware.frontLeftTalon.set(0);
//                Hardware.frontRightTalon.set(0);
//                Hardware.backRightTalon.set(0);
//            }
//            return false;
//        }
//    }
//    
//    //returns the average range of the robot from the nearest object read by the sensor 
//    public static double getRange() {
//        if (Hardware.leftSensor.isEnabled() && Hardware.rightSensor.isEnabled() && Hardware.leftSensor.isRangeValid() && Hardware.rightSensor.isRangeValid()) {
//            double average = (Hardware.leftSensor.getRangeInches() + Hardware.rightSensor.getRangeInches()) / 2;
//            return average;
//        } else {
//            return 0;
//        }
//    }
//}
