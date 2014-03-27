/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008. All Rights Reserved.                             */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/
 
 package edu.wpi.first.wpilibj.templates;
 
 
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
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
     
     Hardware hardware = new Hardware();
     //Increase the array size when threads are added
     private ScibotThread[] teleGroup = {new Drive(), new SwitchCase()};
     private ScibotThread[] autoGroup = {new Vision()};
     private boolean teleRunning, autoRunning;
     
     public void robotInit() {
         //Establish booleans to represent whether the thread group is running, all classes need to extend
         //ScibotThread
         teleRunning = false;
         autoRunning = false;
         
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
