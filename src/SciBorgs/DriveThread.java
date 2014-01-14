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
public class DriveThread {
    
    //GYRO CODE
    private Gyro direction = new Gyro(1);
    double angle;
    
    public double getAngle(){
        return angle;
    }
     
    //Creating jaguars 
    Talon frontright = new Talon(1);
    Talon frontleft = new Talon(2);
    Talon backright = new Talon(3);
    Talon backleft = new Talon(4);
      
    //Creating joysticks
    public Joystick rightjoy = new Joystick(1);
    public Joystick leftjoy = new Joystick(2);
               
    protected void iteration() {
                             
        //Gyro iteration      
        angle = direction.getAngle();
        while(angle>=360){
            angle -= 360;
        }
    
        //Drive iteration
        double rightval = rightjoy.getY();
        double leftval = leftjoy.getY();

        //setting speed
        frontright.set(-1 * rightval);
        backright.set(-1 * rightval);

        frontleft.set(leftval);
        backleft.set(leftval);
        
        //For Gyro
        boolean lButton = rightjoy.getRawButton(4);
        boolean rButton = rightjoy.getRawButton(5);
        
        //Fast turning to go left
        if(lButton) {
            double less = angle + 315;
            
            while (angle < less){
                leftval = -1;
                rightval = 1;   
                        
                backleft.set(leftval);
                frontleft.set(leftval);
                backright.set(-1 * rightval);
                frontright.set(-1 * rightval);   
            }
            Timer.delay(1);
            
            if(lButton) {
                leftval = -1;
                rightval = 1;   
                        
                backleft.set(leftval);
                frontleft.set(leftval);
                backright.set(-1 * rightval);
                frontright.set(-1 * rightval); 
            }
        }
    
        //Fast turning to turn right
        if(rButton) {
            double less1 = angle + 45;
            
            while (angle < less1){
                leftval = 1;
                rightval = -1;
                        
                backleft.set(leftval);
                frontleft.set(leftval);
                backright.set(-1 * rightval);
                frontright.set(-1 * rightval);
            }
            Timer.delay(1);
            
            if(rButton) {
                leftval = 1;
                rightval = -1;
                        
                backleft.set(leftval);
                frontleft.set(leftval);
                backright.set(-1 * rightval);
                frontright.set(-1 * rightval);
            }
         }
       
    }
      
    protected void turnon() {
        System.out.println("Turning on drive");
        direction.reset();
    }
      
    protected void turnoff() {
        System.out.println("Shutting down drive");
    }
    
}
