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
public class Drive extends ScibotThread {
    
    Hardware hardware = new Hardware();
    
    //joystick values for drive
    double rightval;
    double leftval;
              
    public void main() {
                             
        //Drive 
        rightval = hardware.rightjoy.getY();
        leftval = hardware.leftjoy.getY();

        //Setting speed
        hardware.frontright.set(-1 * rightval);
        hardware.backright.set(-1 * rightval);

        hardware.frontleft.set(leftval);
        hardware.backleft.set(leftval);
    }
}
