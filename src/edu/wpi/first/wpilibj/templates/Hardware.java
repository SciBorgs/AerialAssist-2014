/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * Contains all robot components including access to driver station.
 * Reference this class to access components
 * 
 * @author Sciborgs
 */
public class Hardware {
    
    //Jaguars for drive
    public static final Jaguar frontRightTalon = new Jaguar(1);
    public static final Jaguar frontLeftTalon = new Jaguar(2);
    public static final Jaguar backRightTalon = new Jaguar(3);
    public static final Jaguar backLeftTalon = new Jaguar(4);
    
    //Gyro for drive code
    //public static final Gyro gyro = new Gyro(1);
    
    //Joysticks 
    public static final Joystick rightJoy = new Joystick(1);
    public static final Joystick leftJoy = new Joystick(2);
    
    //Pistons
    //public static final DoubleSolenoid piston = new DoubleSolenoid(1,2);
    //public static final Compressor compress = new Compressor(1, 2);
    
    //Ultrasonic sensor
   //public static final Ultrasonic leftSensor = new Ultrasonic(1, 2);
   // public static final Ultrasonic rightSensor = new Ultrasonic(3, 4);

}
