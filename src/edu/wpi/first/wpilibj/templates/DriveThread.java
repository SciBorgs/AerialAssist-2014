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
public class DriveThread extends Thread{
    
    RobotDrive drive = new RobotDrive(2, 4, 1, 3);
    
    //joystick values for drive
    double rightval;
    double leftval;
    
    //Buttons for fast turning
    boolean lButton;
    boolean rButton;

              
    protected void iteration() {

        //Drive iteration
        rightval = Hardware.rightjoy.getY();
        leftval = Hardware.leftjoy.getY();

        drive.tankDrive(leftval, rightval);
        
        //For Gyro
        lButton = Hardware.rightjoy.getRawButton(4);
        rButton = Hardware.rightjoy.getRawButton(5);
        
        //Fast turning to go left
        if(lButton) {
            Hardware.gyro.reset();
            
            while(Hardware.gyro.getAngle() == 0 || Hardware.gyro.getAngle() >= 315) {
                rightval = 1;
                leftval = -1;

                drive.tankDrive(leftval, rightval*-1);
            }
            
            while(lButton) {
                rightval = 1;
                leftval = -1;

                drive.tankDrive(leftval, rightval*-1);
            }
            
            Hardware.gyro.reset();
        }
       
        if(rButton) {
            Hardware.gyro.reset();
            
            while(Hardware.gyro.getAngle() <= 45) {
                rightval = -1;
                leftval = 1;

                drive.tankDrive(leftval, rightval*-1);
            }
            
            while(rButton) {
                rightval = -1;
                leftval = 1;

                drive.tankDrive(leftval, rightval*-1);
            }
            
            Hardware.gyro.reset();
        }

       
    }
      
    protected void turnon() {
        System.out.println("Turning on drive");
        Hardware.gyro.reset();
        Hardware.sensor.setEnabled(true);
    }
      
    protected void turnoff() {
        System.out.println("Shutting down drive");
        Hardware.sensor.setEnabled(false);
        Hardware.compress.stop();
    }
    
}
