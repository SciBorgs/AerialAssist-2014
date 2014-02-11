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
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * Contains all robot components including access to driver station.
 * Reference this class to access components
 * 
 * @author Sciborgs
 */
public class Hardware {
    
    //Talons for drive
    public static Talon frontRightTalon;
    public static Talon frontLeftTalon;
    public static Talon backRightTalon;
    public static Talon backLeftTalon;
    //Robotdrive
    // public static RobotDrive drive = new RobotDrive(2,4,1,3);
    
    //Gyro for drive code
    public static Gyro gyro;
    
    //Joysticks 
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    public static Joystick remote;
    
    public static DriverStationLCD dLCD;
    
    public static Ultrasonic leftSensor;
    public static Ultrasonic rightSensor;
    
    public static Compressor compressor;
    
    public static DoubleSolenoid piston;
    public static DoubleSolenoid gateLatch2;
    public static DoubleSolenoid gateLatch1;
    public static Relay claw;
    //public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
    //public static Compressor compress = new Compressor(1, 2);
    //Camera
    public static AxisCamera camera;
   
}
