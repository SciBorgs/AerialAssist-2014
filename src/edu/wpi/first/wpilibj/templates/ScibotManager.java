/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008. All Rights Reserved.                             */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/
 
 package edu.wpi.first.wpilibj.templates;
 
 
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import java.util.*;
 
 /**
  * The VM is configured to automatically run this class, and to call the
  * functions corresponding to each mode, as described in the SimpleRobot
  * documentation. If you change the name of this class or the package after
  * creating this project, you must also update the manifest file in the resource
  * directory.
  */
 public class ScibotManager extends SimpleRobot{
     /**
      * This function is called once each time the robot enters autonomous mode.
      */
     
//     private Thread thread; //Thread to manage cpu/bandwidth usage
     
     //Increase the array size when threads are added
     private ScibotThread[] teleGroup = {/*new Drive(), */new SwitchCase()};
     private ScibotThread[] autoGroup = {new Vision()};
     private boolean teleRunning, autoRunning;
     
     public void robotInit() {
        Hardware.rightJoy = new Joystick(2);
        Hardware.leftJoy = new Joystick(1);
        Hardware.remote = new Joystick(3);
        
        Hardware.frontRightTalon = new Talon(9);
        Hardware.backRightTalon = new Talon(10);
        Hardware.frontLeftTalon = new Talon(1);
        Hardware.backLeftTalon = new Talon(2);
        //Hardware.clawMotor = new Jaguar(5);
        
//        Hardware.gyro = new Gyro(1);
        Hardware.tempRelay = new Relay(8);
        //double gyroState = Hardware.gyro.getAngle();
        //double uniGyro = Hardware.gyro.getAngle();
        
        Hardware.dLCD = DriverStationLCD.getInstance();
        
//        Hardware.leftSensor = new Ultrasonic(1, 1); //FIX PORT
//        Hardware.rightSensor = new Ultrasonic(1, 1); //FIX PORT
//        
//        Hardware.compressor = new Compressor(1, 8); //updated port
//        
        Hardware.gateLatch = new DoubleSolenoid(1,2);
        Hardware.piston = new DoubleSolenoid(3,4); //updated port
        //Hardware.claw = new DoubleSolenoid(5, 6); //updated port
        
//        Hardware.relay = new Relay(1); //FIX PORT
        
//        Hardware.camera = AxisCamera.getInstance();
         
         //Establish booleans to represent whether the thread group is running, all classes need to extend
         //ScibotThread
         teleRunning = false;
         autoRunning = false;
         
         //Add all neccesary threads to the auto thread group
         //autoGroup.addElement(new <nameOfClass>());
//         autoGroup[0] = new AutoUltrasonic();
//         autoGroup[0] = new Vision();
//         //autoGroup[1] = new AutoUltrasonic();
//         
//         //Add all neccesary threads to the tele thread group
//         //teleGroup.addElement(new <nameOfClass>());
//         teleGroup[0] = new Drive();
//         teleGroup[1] = new SwitchCase();
//         //teleGroup[2] = new Shooter();
         
         //thread = new Thread(this);
         //thread.start();
         
     }
     
     public void disabled() {
        if(teleRunning){
            teleRunning = stopGroup(teleGroup);
        }  
        if(autoRunning){
            autoRunning = stopGroup(autoGroup);
        } 
     }
     
     public void autonomous() {
        if(teleRunning){
            teleRunning = stopGroup(teleGroup);
        } 
        if(!autoRunning){
            autoRunning = startGroup(autoGroup);
        } 
     }
 
     /**
      * This function is called once each time the robot enters operator control.
      */
     public void operatorControl() {
        if(autoRunning){
            autoRunning = stopGroup(autoGroup);
        } 
        if(!teleRunning){
            teleRunning = startGroup(teleGroup);
        } 
     }
     
     //Thread slow down feature is replaced by scibot thread
//     public void run() {
//      while(!thread.isAlive()) {
//           try {
//            Thread.sleep(10);
//           }
//           catch (InterruptedException e) {}
//           //Hardware.dLCD.clear();
//      }
//     }
     
     public boolean startGroup(ScibotThread[] group){
         for(int i = 0; i < group.length; i++){
            group[i].start();
         }
         return true;
     }
     
     public boolean stopGroup(ScibotThread[] group){
        for(int i = 0; i < group.length; i++){
            group[i].stop();
        }
        return false;
     }
 }
