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
import java.util.*;
 
 /**
  * The VM is configured to automatically run this class, and to call the
  * functions corresponding to each mode, as described in the SimpleRobot
  * documentation. If you change the name of this class or the package after
  * creating this project, you must also update the manifest file in the resource
  * directory.
  */
 public class ScibotManager extends SimpleRobot implements Runnable{
     /**
      * This function is called once each time the robot enters autonomous mode.
      */
     
     private Thread thread; //Thread to manage cpu/bandwidth usage
     
     //Increase the array size when threads are added
     private ScibotThread[] teleGroup = new ScibotThread[2];
     private ScibotThread[] autoGroup = new ScibotThread[2];
     private boolean teleRunning, autoRunning;
     
     public void robotInit() {
        Hardware.rightJoy = new Joystick(2);
        Hardware.leftJoy = new Joystick(1);
        
        Hardware.frontRightTalon = new Talon(4);
        Hardware.backRightTalon = new Talon(3);
        Hardware.frontLeftTalon = new Talon(1);
        Hardware.backLeftTalon = new Talon(2);
        
        Hardware.gyro = new Gyro(1);
        double gyroState = Hardware.gyro.getAngle();
        double uniGyro = Hardware.gyro.getAngle();
        
        Hardware.dLCD = DriverStationLCD.getInstance();
        
        Hardware.leftSensor = new Ultrasonic(0, 0); //FIX PORT
        Hardware.rightSensor = new Ultrasonic(0, 0); //FIX PORT
        
        Hardware.compressor = new Compressor(9, 10); //FIX PORT
        
        Hardware.piston1 = new DoubleSolenoid(5, 6); //FIX PORT
        Hardware.piston2 = new DoubleSolenoid(7, 8); //FIX PORT
        Hardware.gateLatch = new Solenoid(7);
        Hardware.relay = new Relay(1); //FIX PORT
         
         //Establish booleans to represent whether the thread group is running, all classes need to extend
         //ScibotThread
         teleRunning = false;
         autoRunning = false;
         
         //Add all neccesary threads to the auto thread group
         //autoGroup.addElement(new <nameOfClass>());
         autoGroup[1] = new AutoUltrasonic();
         
         //Add all neccesary threads to the tele thread group
         //teleGroup.addElement(new <nameOfClass>());
         teleGroup[1] = new Drive();
         
         thread = new Thread(this);
         thread.start();
         
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
     
     public void run() {
      while(!thread.isAlive()) {
           try {
            Thread.sleep(10);
           }
           catch (InterruptedException e) {}
           Hardware.dLCD.clear();
      }
     }
     
     public boolean startGroup(ScibotThread[] group){
         for(int i = 1; i < group.length; i++){
            group[i].start();
         }
         return true;
     }
     
     public boolean stopGroup(ScibotThread[] group){
        for(int i = 1; i < group.length; i++){
            group[i].stop();
        }
        return false;
     }
 }
