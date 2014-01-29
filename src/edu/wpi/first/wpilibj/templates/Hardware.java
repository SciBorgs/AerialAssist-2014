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
import edu.wpi.first.wpilibj.DriverStationLCD;
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
    public static Jaguar frontRightJaguar;
    public static Jaguar frontLeftJaguar;
    public static Jaguar backRightJaguar;
    public static Jaguar backLeftJaguar;
    
    //Robotdrive
//    public static RobotDrive drive = new RobotDrive(2, 4, 1, 3);
    
    //Gyro for drive code
    public static Gyro gyro;
    
    //Joysticks 
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    
    public static DriverStationLCD dLCD;
    //Pistons
    //public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
    //public static Compressor compress = new Compressor(1, 2);
    
    //Ultrasonic sensor
   //public static Ultrasonic leftSensor = new Ultrasonic(1, 2);
   // public static Ultrasonic rightSensor = new Ultrasonic(3, 4);
}
