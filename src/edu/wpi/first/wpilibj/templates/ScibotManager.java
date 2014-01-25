/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008. All Rights Reserved.                             */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/
 
 package edu.wpi.first.wpilibj.templates;
 
 
 import edu.wpi.first.wpilibj.SimpleRobot;
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
     private Object[] teleGroup = new Object[2];
     private Object[] autoGroup = new Object[1];
     
     public static Hardware hardware;
     
     public void robotInit() {
         hardware = new Hardware();
         
         //Establish booleans to represent whether the thread group is running, all classes need to extend
         //ScibotThread
         teleGroup[0] = new Boolean(false);
         autoGroup[0] = new Boolean(false);
         
         //Add all neccesary threads to the auto thread group
         //autoGroup.addElement(new <nameOfClass>());
         
         //Add all neccesary threads to the tele thread group
         //teleGroup.addElement(new <nameOfClass>());
         teleGroup[1] = new Drive();
         
         thread = new Thread(this);
         thread.start();
         
     }
     
     public void autonomous() {
        if(((Boolean) teleGroup[0]).booleanValue()){
            stopGroup(teleGroup);
        } 
        if(!((Boolean) autoGroup[0]).booleanValue()){
            startGroup(autoGroup);
            System.out.println("Autogroup started");
        } 
     }
 
     /**
      * This function is called once each time the robot enters operator control.
      */
     public void operatorControl() {
        if(((Boolean) autoGroup[0]).booleanValue()){
            stopGroup(autoGroup);
        } 
        if(!((Boolean) teleGroup[0]).booleanValue()){
            startGroup(teleGroup);
            System.out.println("Telegroup started");
        } 
     }
     
     public void run() {
      while(!thread.isAlive()) {
       try {
        Thread.sleep(10);
       }
       catch (InterruptedException e) {}
      }
     }
     
     public void startGroup(Object[] group){
         for(int i = 1; i < group.length; i++){
            ((ScibotThread) group[i]).start();
         }
         group[0] = new Boolean(true);
     }
     
     public void stopGroup(Object[] group){
        for(int i = 1; i < group.length; i++){
            ((ScibotThread) group[i]).stop();
        }
        group[0] = new Boolean(true);
     }
 }
