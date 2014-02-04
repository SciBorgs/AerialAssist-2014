/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends ScibotThread {
    
  
    public void start() {
        Hardware.drive.tankDrive(Hardware.leftJoy, Hardware.rightJoy);
    }
    
    public void main() {   
        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.leftJoy.getAxis(Joystick.AxisType.kY)) + " " + String.valueOf(Hardware.rightJoy.getAxis(Joystick.AxisType.kY)));
        Hardware.dLCD.updateLCD();

    }
}
