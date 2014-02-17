/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
//gamepad layout
//http://team358.org/files/programming/ControlSystem2009-/Logitech-F310_ControlMapping.png
public class SwitchCase extends ScibotThread {
    
    private Value[] kValues = {DoubleSolenoid.Value.kForward,
                               DoubleSolenoid.Value.kOff, 
                               DoubleSolenoid.Value.kReverse};
    private String[] kStrings = {   "Forward", 
                                    "Off", 
                                    "Reverse"};
    private String[] names = {  "Piston",
                                "Claw",
                                "Gate Latch"};
    private boolean compressorState = false;
    //Large array that holds controlSurface objects and values
//    private Object[][] controlSurfaces = {
//        //Piston object and values
//        {"Piston", 
//            DoubleSolenoid.Value.kForward,"Forward", 
//            DoubleSolenoid.Value.kOff, "Off", 
//            DoubleSolenoid.Value.kReverse, "Reverse"}, 
//        //Claw objects and values
//        {"Claw", 
//            DoubleSolenoid.Value.kForward,"Forward", 
//            DoubleSolenoid.Value.kOff, "Off", 
//            DoubleSolenoid.Value.kReverse, "Reverse"}, 
//        //GateLatch objects and values
//        {"Gate Latch", 
//            DoubleSolenoid.Value.kForward, "Forward", 
//            DoubleSolenoid.Value.kOff, "Off", 
//            DoubleSolenoid.Value.kReverse, "Reverse"},
//        {"Compressor",
//            "Start", "Start",
//            "Stop", "Stop"}};    
    
    
    private int surfIndex = 0, valIndex = 1;
    
//get button
    public void function() {
        System.out.println("Function SwitchCase running");
        for (int i = 1; i <= 13; i++) {
            iLoveRice(i);
        }
//        clawMotor();
        
        //Show which control surface is selected
        Hardware.dLCD.println(DriverStationLCD.Line.kUser3, 1, "Selected: " + names[surfIndex]);
        Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Value: " + kStrings[valIndex]);
        Hardware.dLCD.updateLCD();
    }
    
    public void iLoveRice(int i){
        System.out.println("Function iLoveRice running");
        if(!Hardware.remote.getRawButton(i)){
            return;
        }
        
        System.out.println("Button Pressed: " + i);
        //functions
        switch(i) {
            case 1:
                valIndex -= 1;
                if(valIndex < 1) valIndex = kValues.length - 1;                   
                break;
                
            case 3:
                valIndex += 1;
                if(valIndex > kValues.length-1) valIndex = 0;
                break;
                
            case 4:
                if(surfIndex == 1) {
                    Hardware.piston.set(kValues[valIndex]);
                }
                else if(surfIndex == 2) {
                    Hardware.claw.set(kValues[valIndex]);
                }
                else if(surfIndex == 3) {
                    Hardware.gateLatch.set(kValues[valIndex]);
                }
                //this would be overrode by the compressor commands in shooter
                else if(surfIndex == 4) {
                    if(compressorState == false) {
                        Hardware.compressor.start();
                        compressorState = !compressorState;
                    }
                    else{
                        Hardware.compressor.stop();
                        compressorState = !compressorState;
                    }
                }
                break;
                
            case 5:
                surfIndex++;
                if(surfIndex > names.length - 1) surfIndex = 0;
                if(valIndex > kValues.length-1) valIndex = 0;
                break;
                  
            case 7:
                surfIndex--;
                if(surfIndex < 0) surfIndex = names.length-1;
                if(valIndex > kValues.length-1) valIndex = 0;
                break;
           
            default:
                break;
        }
        
        //Delay
        Timer.delay(0.5);
    }
    
    //TODO: use left stick to control claw
    public static void clawMotor(){
        if(Hardware.remote.getY() < 0.1){
            Hardware.clawMotor.set(0.5);
        }
        else if(Math.abs(Hardware.remote.getY()) > -0.1){
            Hardware.clawMotor.set(-0.5);
        }
    }
    
    //TODO: make it useable
    public static void clawExtension() {
        if(Hardware.remote.getX() > 0.1) {
            Hardware.piston.set(DoubleSolenoid.Value.kForward);
            Timer.delay(0.5);
            Hardware.piston.set(DoubleSolenoid.Value.kOff);
        }
        else if(Hardware.remote.getX() > -0.1) {
            Hardware.piston.set(DoubleSolenoid.Value.kReverse);
            Timer.delay(0.5);
            Hardware.piston.set(DoubleSolenoid.Value.kOff);
        }
    }
}
