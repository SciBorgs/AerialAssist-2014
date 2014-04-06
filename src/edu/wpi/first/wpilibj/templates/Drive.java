/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Joystick;

public class Drive extends ScibotThread {
    
    double rightVal, leftVal;
    boolean direction = true;
    
    public void function() {   
        //Drive 
        rightVal = Hardware.rightJoy.getY();
        leftVal = Hardware.leftJoy.getY();
        
//        Hardware.drive.tankDrive(leftVal, rightVal);
//        leftVal = leftVal*Math.abs(leftVal);
//        rightVal = rightVal*Math.abs(rightVal);
        
//        if(leftVal > 1) leftVal = 1;
//        if(leftVal < -1) leftVal = -1;
//        if(rightVal > 1) rightVal = 1;
//        if(rightVal < -1) rightVal = -1;
        
//        Hardware.dLCD.println(DriverStationLCD.Line.kUser1, 1, "Right: " + String.valueOf(rightVal));
//        Hardware.dLCD.println(DriverStationLCD.Line.kUser2, 1, "Left: " + String.valueOf(leftVal));
//        Hardware.dLCD.updateLCD();
        //System.out.println(rightVal + ", " + leftVal);
        
//        if(direction){
//        Hardware.drive.tankDrive(leftVal, rightVal);
        if(leftVal > 0.1 || leftVal < -0.1){
            Hardware.frontRightTalon.set(leftVal);
            Hardware.backRightTalon.set(leftVal);
        }else{
            Hardware.frontRightTalon.set(0);
            Hardware.backRightTalon.set(0);
        }
        if(rightVal > 0.1 || rightVal < -0.1){
            Hardware.frontLeftTalon.set(-rightVal);
            Hardware.backLeftTalon.set(-rightVal);
        }else{
            Hardware.frontLeftTalon.set(0);
            Hardware.backLeftTalon.set(0);
        }
//        }else{
//        Hardware.frontRightTalon.set(leftVal);
//        Hardware.backRightTalon.set(leftVal);
//        Hardware.frontLeftTalon.set(-rightVal);
//        Hardware.backLeftTalon.set(-rightVal); 
//        }
        //Switch where the "front" of the robot is
//        if(Hardware.leftJoy.getTrigger()) {
//            direction = !direction;
//        }        
    }
}
