/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Relay;

public class SwitchCase extends ScibotThread {
    
    //Large array that holds controlSurface objects and values
    private Object[][] controlSurfaces = {
        //Piston object and values
        {"Piston", DoubleSolenoid.Value.kForward,
        "Forward", DoubleSolenoid.Value.kOff, "Off", DoubleSolenoid.Value.kReverse, "Reverse"}, 
        //Claw objects and values
        {"Claw", Relay.Value.kForward, "Forward", Relay.Value.kOff, "Off", 
        Relay.Value.kOn, "On", Relay.Value.kReverse, "Reverse"}, 
        //GateLatch objects and values
        {"Gate Latch", DoubleSolenoid.Value.kForward, "Forward", 
         DoubleSolenoid.Value.kOff, "Off", DoubleSolenoid.Value.kReverse, "Reverse"}};
    
    
    private int surfIndex = 0, valIndex = 1;
    
//get button
    public void function() {
        for (int i = 1; i >= 13; i++) {
            iLoveRice(i);
        }
        clawMotor();
        
        //Show which control surface is selected
        Hardware.dLCD.println(DriverStationLCD.Line.kUser3, 1, "Selected: " + controlSurfaces[surfIndex][0]);
        Hardware.dLCD.println(DriverStationLCD.Line.kUser4, 1, "Value: " + controlSurfaces[surfIndex][valIndex+1]);
        
    }
    
    public void iLoveRice(int i){
        if(!Hardware.remote.getRawButton(i)){
            return;
        }
        //functions
        switch(i) {
            case 1:
                valIndex -= 2;
                if(valIndex < 1) valIndex = controlSurfaces.length-2;                   
                break;
                
            case 3:
                valIndex += 2;
                if(valIndex > controlSurfaces.length-2) valIndex = 0;
                break;
                
            case 4:
                if(surfIndex == 1) {
                    Hardware.piston.set((DoubleSolenoid.Value) controlSurfaces[1][valIndex]);
                }
                else if(surfIndex == 2) {
                    Hardware.claw.set((Relay.Value) controlSurfaces[2][valIndex]);
                }
                else if(surfIndex == 3) {
                    Hardware.gateLatch1.set((DoubleSolenoid.Value) controlSurfaces[3][valIndex]);
                    Hardware.gateLatch2.set((DoubleSolenoid.Value) controlSurfaces[3][valIndex]);
                }
                
            case 5:
                surfIndex++;
                if(surfIndex > 2) surfIndex = 0;
                break;
                
            case 7:
                surfIndex--;
                if(surfIndex < 0) surfIndex = 2;
                break;
           
            default:
                break;
        }
    }
    
    public static void clawMotor(){
        if(Hardware.remote.getY(Hand.kRight) < 0){
            Hardware.clawMotor.set(5);
        }
        else if(Hardware.remote.getY(Hand.kRight) > 0){
            Hardware.clawMotor.set(-5);
        }
        else{
            Hardware.clawMotor.set(0);
        }
    }
}
