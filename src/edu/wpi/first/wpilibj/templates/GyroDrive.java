        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Freshman
 */
public class GyroDrive extends ScibotThread {

    //Constant variables for ANGLE
    public static final int ANGLE = 45;

    private double leftVal;
    private double rightVal;

    boolean lButton = Hardware.rightJoy.getRawButton(4);
    boolean rButton = Hardware.rightJoy.getRawButton(5);
    double gyroState = Hardware.gyro.getAngle(); //gyrostate will equal to 0

    boolean check = true;

    public void function() {

        if (check) {

            gyroState = Hardware.gyro.getAngle(); //gyrostate will equal to 0
            if (rButton) {
                check = false;
                Hardware.frontLeftTalon.set(1);
                Hardware.backLeftTalon.set(1);

                Hardware.frontRightTalon.set(1);
                Hardware.backRightTalon.set(1);

            } else if (lButton) {
                check = false;
                Hardware.frontLeftTalon.set(-1);
                Hardware.backLeftTalon.set(-1);

                Hardware.frontRightTalon.set(rightVal);
                Hardware.backRightTalon.set(rightVal);
            }
        }

        if ((gyroState + 45 == Hardware.gyro.getAngle() || 360 + (gyroState - 45) == Hardware.gyro.getAngle()) && !check) {
            Hardware.frontLeftTalon.set(0);
            Hardware.backLeftTalon.set(0);

            Hardware.frontRightTalon.set(0);
            Hardware.backRightTalon.set(0);
            check = true;
        }
    }
}
