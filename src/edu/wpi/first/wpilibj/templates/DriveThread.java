/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 * Teleop code.
 * 
 * @author Freshmen  
 */
public class DriveThread extends ScibotThread{
    
    RobotDrive drive = new RobotDrive(2, 4, 1, 3);
    
    //Joystick Values for drive
    double rightVal;
    double leftVal;
    
    //Buttons for fast turning
    boolean lButton;
    boolean rButton;

              
    public void main() {

        //Drive iteration
        rightVal = Hardware.rightJoy.getY();
        leftVal = Hardware.leftJoy.getY();

        drive.tankDrive(leftVal, rightVal);
        
        //For Gyro
        lButton = Hardware.rightJoy.getRawButton(4);
        rButton = Hardware.rightJoy.getRawButton(5);
        
        //Fast turning to go left
        if(lButton) {
            Hardware.gyro.reset();
            
            while(Hardware.gyro.getAngle() == 0 || Hardware.gyro.getAngle() >= 315) {
                rightVal = 1;
                leftVal = -1;

                drive.tankDrive(leftVal, rightVal*-1);
            }
            
            while(lButton) {
                rightVal = 1;
                leftVal = -1;

                drive.tankDrive(leftVal, rightVal*-1);
            }
            
            Hardware.gyro.reset();
        }
       
        if(rButton) {
            Hardware.gyro.reset();
            
            while(Hardware.gyro.getAngle() <= 45) {
                rightVal = -1;
                leftVal = 1;

                drive.tankDrive(leftVal, rightVal*-1);
            }
            
            while(rButton) {
                rightVal = -1;
                leftVal = 1;

                drive.tankDrive(leftVal, rightVal*-1);
            }
            
            Hardware.gyro.reset();
        }

       
    }
      
    public void turnon() {
        System.out.println("Turning on drive");
        Hardware.gyro.reset();
        Hardware.sensor.setEnabled(true);
    }
      
    public void turnoff() {
        System.out.println("Shutting down drive");
        Hardware.sensor.setEnabled(false);
        Hardware.compress.stop();
    }
    
}
