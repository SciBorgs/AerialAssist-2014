/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SciBorgs;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Teleop code.
 * 
 * @author Freshmen  
 */
public class DriveThread extends Thread{
    
    Hardware hardware = new Hardware();
    
    //joystick values for drive
    double rightval;
    double leftval;
    
    //Buttons for fast turning
    boolean lButton;
    boolean rButton;
    
    //angle for gyro
    double angle;
    
    //fast turning
    double less;
    double less1;
    
    //Button for piston
   // boolean pressed1 = rightjoy.getRawButton(3);
              
    protected void iteration() {
                             
        //Gyro iteration      
        angle = hardware.direction.getAngle();
        while(angle>=360){
            angle -= 360;
        }
    
        //Drive iteration
        rightval = hardware.rightjoy.getY();
        leftval = hardware.leftjoy.getY();

        //setting speed
        hardware.frontright.set(-1 * rightval);
        hardware.backright.set(-1 * rightval);

        hardware.frontleft.set(leftval);
        hardware.backleft.set(leftval);
        
        //For Gyro
        lButton = hardware.rightjoy.getRawButton(4);
        rButton = hardware.rightjoy.getRawButton(5);
        
        ////piston code
        //DoubleSolenoid.Value opened = hardware.piston.get();
        
        //if(pressed1 && opened == DoubleSolenoid.Value.kReverse) {
            //piston.set(DoubleSolenoid.Value.kForward);
        //} else if(pressed1 && opened == DoubleSolenoid.Value.kForward) {
           //piston.set(DoubleSolenoid.Value.kReverse);
        //}
        
        //Fast turning to go left
        if(lButton) {
            less = angle + 315;
            
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
            less1 = angle + 45;
            
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
