/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SciBorgs;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Teleop code.
 * 
 * @author Freshmen #Freshmen4lifeyoloswag720noscopeswag #1sophmoreswag #swagalisious 
 */
public class DriveThread extends Thread{
    
    Hardware hardware = new Hardware();
    
    double angle;
              
    protected void iteration() {
                             
        //Gyro iteration      
        angle = hardware.direction.getAngle();
        while(angle>=360){
            angle -= 360;
        }
    
        //Drive iteration
        double rightval = hardware.rightjoy.getY();
        double leftval = hardware.leftjoy.getY();

        //setting speed
        hardware.frontright.set(-1 * rightval);
        hardware.backright.set(-1 * rightval);

        hardware.frontleft.set(leftval);
        hardware.backleft.set(leftval);
        
        //For Gyro
        boolean lButton = hardware.rightjoy.getRawButton(4);
        boolean rButton = hardware.rightjoy.getRawButton(5);
        
        //Fast turning to go left
        if(lButton) {
            double less = angle + 315;
            
            while (angle < less){
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
            double less1 = angle + 45;
            
            while (angle < less1){
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
    }
      
    protected void turnoff() {
        System.out.println("Shutting down drive");
    }
    
}
