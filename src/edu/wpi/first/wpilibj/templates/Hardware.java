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
    public static Talon frontRightTalon = new Talon(1);
    public static Talon frontLeftTalon = new Talon(3);
    public static Talon backRightTalon = new Talon(2);
    public static Talon backLeftTalon = new Talon(4);
    
    //Robotdrive (1=fl,2=bl,3=fr,4=br)
    // public static RobotDrive drive;
    
    //Gyro for drive code
    //public static Gyro gyro;
    
    //Joysticks 
    public static Joystick rightJoy = new Joystick(1);
    public static Joystick leftJoy = new Joystick(2);
    
    public static DriverStationLCD dLCD = DriverStationLCD.getInstance();
    
    //Pistons
    public static DoubleSolenoid shooterPiston1 = new DoubleSolenoid(1,2);
    public static DoubleSolenoid shooterPiston2 = new DoubleSolenoid(3,4);
    public static Solenoid gateLatch = new Solenoid(3);
    //public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
    public static Compressor compressor = new Compressor(1,2);
    
    //Camera
    public static AxisCamera camera = AxisCamera.getInstance();
    
    //Ultrasonic
    public static Ultrasonic leftSensor = new Ultrasonic(1,2);
    public static Ultrasonic rightSensor = new Ultrasonic(3,4);
   
    //make sense to let the Hardware class handle instantiation
    // public Hardware(){
    //     frontRightTalon = new Talon(3);
    //     backRightTalon = new Talon(4);
    //     frontLeftTalon = new Talon(1);
    //     backLeftTalon = new Talon(2);
        
    //     drive = new RobotDrive(1,2,3,4);//(1=fl,2=bl,3=fr,4=br)
    //     //gyro = new Gyro(1);
    //     dLCD = DriverStationLCD.getInstance();
    //     camera = AxisCamera.getInstance();
    //     rightJoy = new Joystick(1);
    //     leftJoy = new Joystick(2);
    //     shooterPiston1 = new DoubleSolenoid(1,2);
    //     shooterPiston2 = new DoubleSolenoid(3,4);
    //     gateLatch = new Solenoid(3);
    //     compressor = new Compressor(1,2);
    //     leftSensor = new Ultrasonic(1,2);
    //     rightSensor = new Ultrasonic(3,4);
    // }
}
