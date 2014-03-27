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
                                "Gate Latch",
                                "Compressor"};
    private boolean compressorState = false; 
    
    private int surfIndex = 0, valIndex = 1;
    private boolean gate = false;
    
//get button
    public void function() {
        //System.out.println("Function SwitchCase running");
        for (int i = 1; i <= 13; i++) {
            iLoveRice(i);
        }
        
        //Show which control surface is selected
        Hardware.dLCD.clear();
        Hardware.dLCD.println(DriverStationLCD.Line.kUser3, 1, "Selected: " + names[surfIndex]);
        Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Value: " + kStrings[valIndex]);
        Hardware.dLCD.println(DriverStationLCD.Line.kUser5, 1, "Limit val: " + Hardware.limit.getValue());
        Hardware.dLCD.println(DriverStationLCD.Line.kUser6, 1, "piston val: " + Hardware.piston.get());
        Hardware.dLCD.updateLCD();
    }
    
    public void iLoveRice(int i){
        //System.out.println("Function iLoveRice running");
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
                if(surfIndex == 0) {
                    Hardware.piston.set(kValues[valIndex]);
                }
                else if(surfIndex == 1) {
//                    Hardware.claw.set(kValues[valIndex]);
                }
                else if(surfIndex == 2) {
                    Hardware.gateLatch.set(kValues[valIndex]);
                }
                //this would be overrode by the compressor commands in shooter
                else if(surfIndex == 3) {
//                    System.out.println(compressorState);
                    if(compressorState == false) {
//                        Hardware.compressor.setRelayValue(Relay.Value.kOn);
                        Hardware.tempRelay.set(Relay.Value.kForward);
                        compressorState = !compressorState;
                    }
                    else{
                        Hardware.tempRelay.set(Relay.Value.kOff);
//                        Hardware.compressor.setRelayValue(Relay.Value.kOff);
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
        Timer.delay(0.3);
    }
    
    public void rice2(int i){
        if(!Hardware.remote.getRawButton(i)){
            return;
        }
        
        System.out.println("Button Pressed: " + i);
        //functions
        switch(i) {
            case 1:
                if(gate){
                    Hardware.gateLatch.set(Value.kForward);
                }else{
                    Hardware.gateLatch.set(Value.kReverse);
                }
                Timer.delay(0.5);
                    Hardware.gateLatch.set(Value.kOff);
                gate=!gate;
                break;
            
            case 2:
                Hardware.piston.set(Value.kReverse);
                break;
            case 3:
                Hardware.piston.set(Value.kOff);
                break;
            case 4:
                Hardware.piston.set(Value.kForward);
                break;
            default:
                break;
        }
        
        //Delay
        Timer.delay(0.3);
    }
}
