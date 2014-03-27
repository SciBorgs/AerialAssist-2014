/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
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
    public static Relay tempRelay;
    
    public static DoubleSolenoid piston;
    public static DoubleSolenoid gateLatch;
    //public static Compressor compress = new Compressor(1, 2);
    //Camera
    public static AxisCamera camera;
    public static AnalogChannel limit;
    
    public Hardware(){
        rightJoy = new Joystick(2);
        leftJoy = new Joystick(1);
        remote = new Joystick(3);
        
        frontRightTalon = new Talon(9);
        backRightTalon = new Talon(10);
        frontLeftTalon = new Talon(1);
        backLeftTalon = new Talon(2);
        
        tempRelay = new Relay(8);
        
        dLCD = DriverStationLCD.getInstance();
        
//        Hardware.leftSensor = new Ultrasonic(1, 1); //FIX PORT
//        Hardware.rightSensor = new Ultrasonic(1, 1); //FIX PORT
//        Hardware.compressor = new Compressor(1, 8); //updated port
        
        gateLatch = new DoubleSolenoid(1,2);
        gateLatch.set(DoubleSolenoid.Value.kOff);
        piston = new DoubleSolenoid(3,4); //updated port
        piston.set(DoubleSolenoid.Value.kOff);
        //Hardware.claw = new DoubleSolenoid(5, 6); //updated port
        limit = new AnalogChannel(1);
        
//        Hardware.relay = new Relay(1); //FIX PORT
        
//        Hardware.camera = AxisCamera.getInstance();
    }
}
