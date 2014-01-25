/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Teleop code.
 * 
 * @author Freshmen  
 */
public class Drive extends ScibotThread {
    
    //joystick Values for drive
    double rightVal;
    double leftVal;
              
    public void main() {
                             
        //Drive 
        rightVal = Hardware.rightJoy.getY();
        leftVal = Hardware.leftJoy.getY();

        //Setting speed
        Hardware.frontRightTalon.set(-1 * rightVal);
        Hardware.backRightTalon.set(-1 * rightVal);

        Hardware.frontLeftTalon.set(leftVal);
        Hardware.backLeftTalon.set(leftVal);
    }
}
