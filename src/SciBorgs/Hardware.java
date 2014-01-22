/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SciBorgs;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * Contains all robot components including access to driver station.
 * Reference this class to access components
 * 
 * @author Sciborgs
 */
public class Hardware {
    
    //Jaguars for drive
    Talon frontright = new Talon(1);
    Talon frontleft = new Talon(2);
    Talon backright = new Talon(3);
    Talon backleft = new Talon(4);
    
    //Gyro for drive code
    public Gyro direction = new Gyro(1);
    
    //Joysticks 
    public Joystick rightjoy = new Joystick(1);
    public Joystick leftjoy = new Joystick(2);
    
}
