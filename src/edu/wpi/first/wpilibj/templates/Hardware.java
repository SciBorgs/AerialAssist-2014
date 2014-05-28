/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
//All hardware imports
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
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Contains all robot components including access to driver station.
 * Reference this class to access components
 * 
 * @author Sciborgs
 */
public class Hardware {
    
    //Declares Talons for drive code
    public static Talon frontRightTalon;
    public static Talon frontLeftTalon;
    public static Talon backRightTalon;
    public static Talon backLeftTalon;
    
    //Declares Gyro for gyrodrive code
//    public static Gyro gyro;
    
    //Declares Joysticks 
    public static Joystick rightJoy;
    public static Joystick leftJoy;
    public static Joystick remote;
    
    public static DriverStationLCD dLCD;
    
//    public static Ultrasonic leftSensor;
//    public static Ultrasonic rightSensor;
    //Declares compressor
    public static Compressor compressor;
//    public static Relay tempRelay;
    //Declares solenoids
    public static DoubleSolenoid piston;
    public static DoubleSolenoid gateLatch;
    //public static Compressor compress = new Compressor(1, 2);
    //Camera
    //Declares drive as an object of class RobotDrive
    public static RobotDrive drive;
//    public static Relay topMotor;
    
    public Hardware(){
        //initializing all the harware
        //and assigning pins/ports
        rightJoy = new Joystick(2); //sets right joystick to port 2
        leftJoy = new Joystick(1); //sets left joystick to port 1
        remote = new Joystick(3); //sets remote to port 3
        
        frontRightTalon = new Talon(9);//sets front right talon channel to 9
        backRightTalon = new Talon(10);//sets back right talon channel to 10
        frontLeftTalon = new Talon(1);//sets front left talon channel to 1
        backLeftTalon = new Talon(2);//sets back left talon channel to 2
//        drive = new RobotDrive(1,2,9,10);
//        tempRelay = new Relay(1);
        
        dLCD = DriverStationLCD.getInstance();
        
//        Hardware.leftSensor = new Ultrasonic(1, 1); //FIX PORT
//        Hardware.rightSensor = new Ultrasonic(1, 1); //FIX PORT
        compressor = new Compressor(10, 1); //updated port- sets channels for pressureswitch to 10 and relay to 1
        
        gateLatch = new DoubleSolenoid(1,2);//sets cforward channel to 1 and reverse channel to 2
        gateLatch.set(DoubleSolenoid.Value.kOff);//turns gatelatch completely off 
        piston = new DoubleSolenoid(3,4); //updated port-sets forward channel to 3 and reverse channel to 4
        piston.set(DoubleSolenoid.Value.kOff);//turns piston completely off
        //Hardware.claw = new DoubleSolenoid(5, 6); //updated port
//        limit = new AnalogChannel(1);
        
//        Hardware.relay = new Relay(1); //FIX PORT
    }
}
