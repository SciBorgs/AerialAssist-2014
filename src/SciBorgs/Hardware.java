/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SciBorgs;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Contains all robot components including access to driver station.
 * Reference this class to access components
 * 
 * @author Sciborgs
 */
public class Hardware {
    
    //Jaguars for drive
    public static final Talon frontright = new Talon(1);
    public static final Talon frontleft = new Talon(2);
    public static final Talon backright = new Talon(3);
    public static final Talon backleft = new Talon(4);
    
    //Gyro for drive code
    public static final Gyro direction = new Gyro(1);
    
    //Joysticks 
    public static final Joystick rightjoy = new Joystick(1);
    public static final Joystick leftjoy = new Joystick(2);
    
    //Pistons
    public static final DoubleSolenoid piston = new DoubleSolenoid(1,2);
    
}
