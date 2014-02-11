/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;

public class SwitchCase extends ScibotThread {

//get button
    public void function() {
        for (int i = 1; i >= 13; i++) {
            iLoveRice(i);
        }
    }
    
    public void iLoveRice(int i){
        if(!Hardware.remote.getRawButton(i)){
            return;
        }
        //functions
        switch(i){
            //Controls piston
            case 1:
                if(Hardware.piston.get() == DoubleSolenoid.Value.kForward) {
                    Hardware.piston.set(DoubleSolenoid.Value.kReverse);
                    break;
                }
                Hardware.piston.set(DoubleSolenoid.Value.kForward);
                break;
                
            //Controls claw
            case 2:
                if(Hardware.claw.get() == Relay.Value.kOff) {
                    Hardware.claw.set(Relay.Value.kOn);
                    break;
                }
                Hardware.claw.set(Relay.Value.kOff);
                break;
                
            //Controls gateLatch
            case 3:
                if(Hardware.gateLatch1.get() == DoubleSolenoid.Value.kForward) {
                    Hardware.gateLatch1.set(DoubleSolenoid.Value.kReverse);
                    Hardware.gateLatch2.set(DoubleSolenoid.Value.kReverse);
                    break;
                }
                Hardware.gateLatch1.set(DoubleSolenoid.Value.kForward);
                Hardware.gateLatch2.set(DoubleSolenoid.Value.kForward);
                break;
                
            //Controls claw direction
            case 4:
                if(Hardware.claw.get() == Relay.Value.kForward) {
                    Hardware.claw.set(Relay.Value.kReverse);
                    break;
                }
                Hardware.claw.set(Relay.Value.kForward);
                break;
            case 5:
                
                break;
            case 7:
                break;
            
            default:
                break;
        }
    }
}
