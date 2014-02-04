package edu.wpi.first.wpilibj.templates;

public class Ultrasonic extends ScibotThread {

    double leftValue = Hardware.leftSensor.getRangeInches();
    double rightValue = Hardware.rightSensor.getRangeInches();
    double avgValue = (leftValue + rightValue) / 2;

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

    public void travel(double distance) {
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
    }
}
