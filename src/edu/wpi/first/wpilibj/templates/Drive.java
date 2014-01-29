/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
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

//        Hardware.drive.tankDrive(leftVal, rightVal);
        Hardware.frontRightJaguar.set(leftVal);
        Hardware.backRightJaguar.set(leftVal);
        Hardware.frontLeftJaguar.set(-rightVal);
        Hardware.backLeftJaguar.set(-rightVal);
        
        
        //Setting speed
//        Hardware.frontRightJaguar.set(-1 * rightVal);
//        Hardware.backRightJaguar.set(-1 * rightVal);
//
//        Hardware.frontLeftJaguar.set(leftVal);
//        Hardware.backLeftJaguar.set(leftVal);
    }
}
