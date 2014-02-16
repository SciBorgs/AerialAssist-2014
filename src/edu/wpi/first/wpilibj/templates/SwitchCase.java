/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class SwitchCase extends ScibotThread {
    
    //TODO: Fix array
    //Large array that holds controlSurface objects and values
    private Object[][] controlSurfaces = {
        //Piston object and values
        {"Piston", 
            DoubleSolenoid.Value.kForward,"Forward", 
            DoubleSolenoid.Value.kOff, "Off", 
            DoubleSolenoid.Value.kReverse, "Reverse"}, 
        //Claw objects and values
        {"Claw", 
            DoubleSolenoid.Value.kForward,"Forward", 
            DoubleSolenoid.Value.kOff, "Off", 
            DoubleSolenoid.Value.kReverse, "Reverse"}, 
        //GateLatch objects and values
        {"Gate Latch", 
            DoubleSolenoid.Value.kForward, "Forward", 
            DoubleSolenoid.Value.kOff, "Off", 
            DoubleSolenoid.Value.kReverse, "Reverse"},
        {"Compressor",
            "Start", "Start",
            "Stop", "Stop"}};    
    
    
    private int surfIndex = 0, valIndex = 1;
    
//get button
    public void function() {
        System.out.println("FUnction SwitchCase running");
        for (int i = 1; i <= 13; i++) {
            iLoveRice(i);
        }
//        clawMotor();
        
        //Show which control surface is selected
        Hardware.dLCD.println(DriverStationLCD.Line.kUser3, 1, "Selected: " + controlSurfaces[surfIndex][0]);
        Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Value: " + controlSurfaces[surfIndex][valIndex+1]);
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
                valIndex -= 2;
                if(valIndex < 1) valIndex = controlSurfaces[surfIndex].length-2;                   
                break;
                
            case 3:
                valIndex += 2;
                if(valIndex > controlSurfaces[surfIndex].length-2) valIndex = 0;
                break;
                
            case 4:
                if(surfIndex == 1) {
                    Hardware.piston.set((DoubleSolenoid.Value) controlSurfaces[1][valIndex]);
                }
                else if(surfIndex == 2) {
                    Hardware.claw.set((DoubleSolenoid.Value) controlSurfaces[2][valIndex]);
                }
                else if(surfIndex == 3) {
                    Hardware.gateLatch.set((DoubleSolenoid.Value) controlSurfaces[3][valIndex]);
                }
                else if(surfIndex == 4) {
                    if(valIndex == 1) {
                        Hardware.compressor.start();
                    }
                    else if(valIndex == 2) {
                        Hardware.compressor.stop();
                    }
                }
                break;
                
            case 5:
                surfIndex++;
                if(surfIndex > controlSurfaces.length-3) surfIndex = 0;
                if(valIndex > controlSurfaces[surfIndex].length-1) valIndex = 1;
                break;
                
            case 7:
                surfIndex--;
                if(surfIndex < 0) surfIndex = controlSurfaces.length-1;
                if(valIndex > controlSurfaces[surfIndex].length-1) valIndex = 1;
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
