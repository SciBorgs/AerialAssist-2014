        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

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

    public void main() {
        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.gyro.getAngle()));
        Hardware.dLCD.updateLCD();
        /*
        if (check) {

            gyroState = Hardware.gyro.getAngle(); //gyrostate will equal to 0
            if (rButton) {
                check = false;
                Hardware.frontLeftJaguar.set(1);
                Hardware.backLeftJaguar.set(1);

                Hardware.frontRightJaguar.set(1);
                Hardware.backRightJaguar.set(1);

            } else if (lButton) {
                check = false;
                Hardware.frontLeftJaguar.set(-1);
                Hardware.backLeftJaguar.set(-1);

                Hardware.frontRightJaguar.set(rightVal);
                Hardware.backRightJaguar.set(rightVal);
            }
        }

        if ((gyroState + 45 == Hardware.gyro.getAngle() || 360 + (gyroState - 45) == Hardware.gyro.getAngle()) && !check) {
            Hardware.frontLeftJaguar.set(0);
            Hardware.backLeftJaguar.set(0);

            Hardware.frontRightJaguar.set(0);
            Hardware.backRightJaguar.set(0);
            check = true;
        }
        */
    }
}
