package edu.wpi.first.wpilibj.templates;

public class Ultrasonic extends ScibotThread {

    double leftValue = Hardware.leftSensor.getRangeInches();
    double rightValue = Hardware.rightSensor.getRangeInches();
    double avgValue = (leftValue + rightValue) / 2;
    final double BUFFER = 1;
    double distance = 1;
    boolean ready = false;
    
    public void start() {
        ready = false;
        super.start();
    }
    
    public void function() {
        if(!Vision.targetHot.getValue()) return; //If the goal is not hot yet
        
        if(!ready) {
            while(Math.abs(leftValue - rightValue) > BUFFER) {
                align();
            }
            while(!ready) {
                ready = travel();
            }
        }
        else {
            Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Ready to shoot.", );
        }
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
}
