/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008. All Rights Reserved.                             */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/
 
 package Sciborgs;
 
 
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
     private Vector teleGroup = new Vector();
     private Vector autoGroup = new Vector();
     
     public void startCompetition() {
         //Establish booleans to represent whether the thread group is running, all classes need to extend
         //ScibotThread
         teleGroup.setElementAt(new Boolean(false), 0);
         autoGroup.setElementAt(new Boolean(false), 0);
         
         //Add all neccesary threads to the auto thread group
         //autoGroup.add(new ScibotThread(new <nameOfClass>()));
         
         //Add all neccesary threads to the tele thread group
         //teleGroup.add(new ScibotThread(new <nameOfClass>()));
         teleGroup.add(new ScibotThread(new Drive()));
         
         super.startCompetition();
         thread = new Thread(this);
         thread.start();
         
     }
     
     public void autonomous() {
        if(((Boolean) teleGroup.firstElement()).booleanValue()){
            stopGroup(teleGroup);
        } 
        if(!((Boolean) autoGroup.firstElement()).booleanValue()){
            startGroup(autoGroup);
        } 
     }
 
     /**
      * This function is called once each time the robot enters operator control.
      */
     public void operatorControl() {
        if(((Boolean) autoGroup.firstElement()).booleanValue()){
            stopGroup(autoGroup);
        } 
        if(!((Boolean) teleGroup.firstElement()).booleanValue()){
            startGroup(teleGroup);
        } 
     }
     
     public void run() {
      while(!thread.isInterrupted()) {
       try {
        Thread.sleep(1);
       }
       catch (InteruptedException e) {}
      }
     }
     
     public void startGroup(Vector group){
         for(int i = 1; i < group.size(); i++){
            ((ScibotThread) group.elementAt(i)).start();
         }
         group.setElementAt(new Boolean(true), 0);
     }
     
     public void stopGroup(Vector group){
        for(int i = 1; i < group.size(); i++){
            ((ScibotThread) group.elementAt(i)).stop();
        }
        group.setElementAt(new Boolean(false), 0);
     }
 }
