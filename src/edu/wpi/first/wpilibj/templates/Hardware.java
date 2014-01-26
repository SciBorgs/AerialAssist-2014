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
    public static Jaguar frontRightTalon;
    public static Jaguar frontLeftTalon;
    public static Jaguar backRightTalon;
    public static Jaguar backLeftTalon;
    
    //Robotdrive
// public static RobotDrive drive = new RobotDrive(2, 4, 1, 3);
    
    //Gyro for drive code
    //public static Gyro gyro = new Gyro(1);
    
    //Joysticks
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    
    //Pistons
    //public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
    //public static Compressor compress = new Compressor(1, 2);
    
    //Ultrasonic sensor
   //public static Ultrasonic leftSensor = new Ultrasonic(1, 2);
   // public static Ultrasonic rightSensor = new Ultrasonic(3, 4);
    public void Hardware() {
// drive = new RobotDrive(2, 4, 1, 3);
        rightJoy = new Joystick(1);
        leftJoy = new Joystick(2);
        
        frontRightTalon = new Jaguar(1);
        frontLeftTalon = new Jaguar(2);
        backRightTalon = new Jaguar(3);
        backLeftTalon = new Jaguar(4);
    }
}
