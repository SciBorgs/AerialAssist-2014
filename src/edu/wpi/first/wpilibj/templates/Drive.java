/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends ScibotThread {
    
    public static double rightVal = 0, leftVal = 0;
    public static boolean takeOver = false; //allow restriction on driver control
    
    public void function() {
        //Drive
        if(!takeOver){
            rightVal = Hardware.rightJoy.getY();
            leftVal = Hardware.leftJoy.getY();
        }
        
        //        leftVal = leftVal*Math.abs(leftVal);
        //        rightVal = rightVal*Math.abs(rightVal);
        //
        //        if(leftVal > 1) leftVal = 1;
        //        if(leftVal < -1) leftVal = -1;
        //        if(rightVal > 1) rightVal = 1;
        //        if(rightVal < -1) rightVal = -1;
        
        Hardware.dLCD.println(DriverStationLCD.Line.kUser1, 1, "Right: " + String.valueOf(rightVal));
        Hardware.dLCD.println(DriverStationLCD.Line.kUser2, 1, "Left: " + String.valueOf(leftVal));
        //        System.out.println(rightVal + ", " + leftVal);
        
        //        if(rightVal > 1) rightVal = 1;
        //        if(rightVal < -1) rightVal = -1;
        //        if(leftVal > 1) leftVal = 1;
        //        if(leftVal < -1) leftVal = -1;
        
        //        rightVal *= Math.abs(rightVal);
        //        leftVal *= Math.abs(leftVal);
        
        Hardware.drive.tankDrive(leftVal, rightVal);
        //        Hardware.frontRightTalon.set(rightVal);
        //        Hardware.backRightTalon.set(rightVal);
        //        Hardware.frontLeftTalon.set(-leftVal);
        //        Hardware.backLeftTalon.set(-leftVal);
        
    }
}
