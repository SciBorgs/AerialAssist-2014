/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Yoli
 */
public class PneumaticsClass {
    
   Hardware hardware = new Hardware();
    
    //Button for piston
   boolean pressed1 = hardware.rightjoy.getRawButton(3);
   boolean pressed2 = hardware.rightjoy.getRawButton(7);
   boolean pressed3 = hardware.rightjoy.getRawButton(8);
    public void iteration() {
        
        //compressor code
        if (pressed2 && hardware.compress.getPressureSwitchValue() == false){
            hardware.compress.start();       
        }
        if (hardware.compress.getPressureSwitchValue() == true || pressed3){
            hardware.compress.stop();
        }
        
        //piston code
        DoubleSolenoid.Value opened = hardware.piston.get();
        
        if(pressed1 && opened == DoubleSolenoid.Value.kReverse) {
            hardware.piston.set(DoubleSolenoid.Value.kForward);
        } else if(pressed1 && opened == DoubleSolenoid.Value.kForward) {
           hardware.piston.set(DoubleSolenoid.Value.kReverse);
        
        }
    
    }
    
}
