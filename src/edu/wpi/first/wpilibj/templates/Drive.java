/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends ScibotThread {
    
    double rightVal, leftVal;
    
    public void main() {   
        //Drive
        rightVal = ScibotManager.hardware.rightJoy.getY();
        leftVal = ScibotManager.hardware.leftJoy.getY();
        
        if(rightVal > 1) rightVal = 1;
        if(rightVal < -1) rightVal = -1;
        if(leftVal > 1) leftVal = 1;
        if(leftVal < -1) leftVal = -1;

        rightVal *= Math.abs(rightVal);
        leftVal *= Math.abs(leftVal);

        ScibotManager.hardware.frontRightTalon.set(rightVal);
        ScibotManager.hardware.backRightTalon.set(rightVal);
        ScibotManager.hardware.frontLeftTalon.set(-leftVal);
        ScibotManager.hardware.backLeftTalon.set(-leftVal);
        
        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, String.valueOf(Hardware.leftJoy.getAxis(Joystick.AxisType.kY)) + " " + String.valueOf(Hardware.rightJoy.getAxis(Joystick.AxisType.kY)));
        Hardware.dLCD.updateLCD();

    }
}
