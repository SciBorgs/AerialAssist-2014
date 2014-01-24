/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SciBorgs;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.RobotDrive;
/**
 * Teleop code.
 * 
 * @author Freshmen  
 */
public class DriveThread extends Thread{
    
    Hardware hardware = new Hardware();
    
    RobotDrive drive = new RobotDrive(2, 4, 1, 3);
    
    //joystick values for drive
    double rightval;
    double leftval;
    
    //Buttons for fast turning
    boolean lButton;
    boolean rButton;
  
    //fast turning
    double less;
    double less1;
              
    protected void iteration() {

        //Drive iteration
        rightval = hardware.rightjoy.getY();
        leftval = hardware.leftjoy.getY();

        drive.tankDrive(leftval, rightval);
        
        //For Gyro
        lButton = hardware.rightjoy.getRawButton(4);
        rButton = hardware.rightjoy.getRawButton(5);
         
        //Fast turning to go left
        if(lButton) {
            less = hardware.gyro.returnAngle() + 315;
            
            while (hardware.gyro.returnAngle() < less){
                leftval = -1;
                rightval = 1;   
                        
                hardware.backleft.set(leftval);
                hardware.frontleft.set(leftval);
                hardware.backright.set(-1 * rightval);
                hardware.frontright.set(-1 * rightval);   
            }
            Timer.delay(1);
            
            if(lButton) {
                leftval = -1;
                rightval = 1;   
                        
                hardware.backleft.set(leftval);
                hardware.frontleft.set(leftval);
                hardware.backright.set(-1 * rightval);
                hardware.frontright.set(-1 * rightval); 
            }
        }
    
        //Fast turning to turn right
        if(rButton) {
            less1 = hardware.gyro.returnAngle() + 45;
            
            while (hardware.gyro.returnAngle() < less1){
                leftval = 1;
                rightval = -1;
                        
                hardware.backleft.set(leftval);
                hardware.frontleft.set(leftval);
                hardware.backright.set(-1 * rightval);
                hardware.frontright.set(-1 * rightval);
            }
            Timer.delay(1);
            
            if(rButton) {
                leftval = 1;
                rightval = -1;
                        
                hardware.backleft.set(leftval);
                hardware.frontleft.set(leftval);
                hardware.backright.set(-1 * rightval);
                hardware.frontright.set(-1 * rightval);
            }
         }
       
    }
      
    protected void turnon() {
        System.out.println("Turning on drive");
        hardware.direction.reset();
        hardware.sensor.setEnabled(true);
    }
      
    protected void turnoff() {
        System.out.println("Shutting down drive");
        hardware.sensor.setEnabled(false);
        hardware.compress.stop();
    }
    
}
