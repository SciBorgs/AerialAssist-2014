        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;

public class GyroDrive extends ScibotThread {

    //Constant variables for ANGLE
    public static final int ANGLE = 45;

    boolean lButton = Hardware.rightJoy.getRawButton(4);
    boolean rButton = Hardware.rightJoy.getRawButton(5);

    public void main() {
        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.gyro.getAngle()));
        Hardware.dLCD.updateLCD();
        Hardware.gyro.reset();
        
        
    }
}
