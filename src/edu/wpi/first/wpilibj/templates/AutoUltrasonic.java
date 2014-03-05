package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

public class AutoUltrasonic extends ScibotThread {

    double leftValue = Hardware.leftSensor.getRangeInches();
    double rightValue = Hardware.rightSensor.getRangeInches();
    double avgValue = (leftValue + rightValue) / 2;
    final double BUFFER = 1;
    double distance = 1;
    boolean ready = false;
    
    //Overriden start method, resets ready to false to restart autonomous cycle
    public void start() {
        ready = false;
        super.start();
    }
    
    //Function that aligns and gets the robot to the proper distance, then 
    //
    public void function() {
        if(!ready) {
            while(Math.abs(leftValue - rightValue) > BUFFER) {
                align();
            }
            while(!ready) {
                ready = travel();
            }
        }
        else {
            Shooter.shoot();
            while(Math.abs(leftValue - rightValue) > BUFFER) {
                align();
            }
            //Reassign distance to 2
            distance = 2;
            while(!ready) {
                ready = travel();
            }
        }
        stop();
    }
    
    public void align() {
        double speed = (leftValue - rightValue) * 0.1;
        if (leftValue - rightValue > 0) {
            Hardware.backLeftTalon.set(speed);
            Hardware.frontLeftTalon.set(speed);
            Hardware.frontRightTalon.set(-speed);
            Hardware.backRightTalon.set(-speed);
        } else if (rightValue - leftValue > 0) {
            Hardware.backLeftTalon.set(-speed);
            Hardware.frontLeftTalon.set(-speed);
            Hardware.frontRightTalon.set(speed);
            Hardware.backRightTalon.set(speed);
        } else {
            Hardware.backLeftTalon.set(0);
            Hardware.frontLeftTalon.set(0);
            Hardware.frontRightTalon.set(0);
            Hardware.backRightTalon.set(0);
        }
    }

    public boolean travel() {
        if(Math.abs(distance - avgValue) > BUFFER) {
            return true;
        }
        else {
            double speed = -((distance - avgValue) / distance);
            if (distance - avgValue > 0) {
                Hardware.backLeftTalon.set(speed);
                Hardware.frontLeftTalon.set(speed);
                Hardware.frontRightTalon.set(speed);
                Hardware.backRightTalon.set(speed);
            } else if (distance - avgValue < 0) {
                Hardware.backLeftTalon.set(speed);
                Hardware.frontLeftTalon.set(speed);
                Hardware.frontRightTalon.set(speed);
                Hardware.backRightTalon.set(speed);
            } else {
                Hardware.backLeftTalon.set(0);
                Hardware.frontLeftTalon.set(0);
                Hardware.frontRightTalon.set(0);
                Hardware.backRightTalon.set(0);
            }
            return false;
        }
    }
    
    public static double getRange() {
        if (Hardware.leftSensor.isEnabled() && Hardware.rightSensor.isEnabled() && Hardware.leftSensor.isRangeValid() && Hardware.rightSensor.isRangeValid()) {
            double average = (Hardware.leftSensor.getRangeInches() + Hardware.rightSensor.getRangeInches()) / 2;
            return average;
        } else {
            return 0;
        }
    }
}
