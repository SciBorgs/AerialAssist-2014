package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

public class Ultrasonic extends ScibotThread {

    double leftValue;
    double rightValue;
    double avgValue = (leftValue + rightValue) / 2;
    final double BUFFER = 1;
    double distance = 1;
    public static boolean ready;
    public static boolean inPosition;
    
    public Ultrasonic() {
        ready = false;
        inPosition = false;
    }
    
    public void function() {
        leftValue = Hardware.leftSensor.getRangeInches();
        rightValue = Hardware.rightSensor.getRangeInches();
        if(leftValue == 0 || rightValue == 0){
            Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Ultrasonic NOT in range");
            ready = true;
        }else{
            Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Ultrasonic in range");
            ready = inPosition = false;
        }
        //align and travel only happens if untrasonic is in range and button 3 of left joystick is pressed
        if(ready && Hardware.leftJoy.getRawButton(3)) {
            //restrict driver control of the robot
            Drive.takeOver = true;
            while(Math.abs(leftValue - rightValue) > BUFFER) {
                align();
            }
            boolean tr = false;
            while(!tr) {
                tr = travel();
            }
            inPosition = true;
            Drive.takeOver = false;
        }
    }
    
    public void align() {
        double speed = (leftValue - rightValue) * 0.1;
        if (leftValue - rightValue > 0) {
            Drive.leftVal = speed;
            Drive.rightVal = -speed;
//            Hardware.drive.tankDrive(speed, -speed);
//            Hardware.backLeftTalon.set(speed);
//            Hardware.frontLeftTalon.set(speed);
//            Hardware.frontRightTalon.set(-speed);
//            Hardware.backRightTalon.set(-speed);
        } else if (rightValue - leftValue > 0) {
            Drive.leftVal = -speed;
            Drive.rightVal = speed;
//            Hardware.drive.tankDrive(-speed, speed);
//            Hardware.backLeftTalon.set(-speed);
//            Hardware.frontLeftTalon.set(-speed);
//            Hardware.frontRightTalon.set(speed);
//            Hardware.backRightTalon.set(speed);
        } else {
            Drive.leftVal = 0;
            Drive.rightVal = 0;
//            Hardware.drive.tankDrive(0, 0);
//            Hardware.backLeftTalon.set(0);
//            Hardware.frontLeftTalon.set(0);
//            Hardware.frontRightTalon.set(0);
//            Hardware.backRightTalon.set(0);
        }
    }

    public boolean travel() {
        if(Math.abs(distance - avgValue) > BUFFER) {
            return true;
        }
        else {
            double speed = -((distance - avgValue) / distance);
            if (distance - avgValue > 0) {
                Drive.leftVal = speed;
                Drive.rightVal = speed;
//                Hardware.drive.tankDrive(speed, speed);
//                Hardware.backLeftTalon.set(speed);
//                Hardware.frontLeftTalon.set(speed);
//                Hardware.frontRightTalon.set(speed);
//                Hardware.backRightTalon.set(speed);
            } else if (distance - avgValue < 0) {
                Drive.leftVal = -speed;
                Drive.rightVal = -speed;
//                Hardware.drive.tankDrive(speed, speed);
//                Hardware.backLeftTalon.set(speed);
//                Hardware.frontLeftTalon.set(speed);
//                Hardware.frontRightTalon.set(speed);
//                Hardware.backRightTalon.set(speed);
            } else {
                Drive.leftVal = 0;
                Drive.rightVal = 0;
//                Hardware.drive.tankDrive(0, 0);
//                Hardware.backLeftTalon.set(0);
//                Hardware.frontLeftTalon.set(0);
//                Hardware.frontRightTalon.set(0);
//                Hardware.backRightTalon.set(0);
            }
            return false;
        }
    }
}
