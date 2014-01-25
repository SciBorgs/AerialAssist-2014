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
    
    //Button for piston
   boolean pressed1 = Hardware.rightjoy.getRawButton(3);
   boolean pressed2 = Hardware.rightjoy.getRawButton(7);
   boolean pressed3 = Hardware.rightjoy.getRawButton(8);
    public void iteration() {
        
        //compressor code
        if (pressed2 && Hardware.compress.getPressureSwitchValue() == false){
            Hardware.compress.start();       
        }
        if (Hardware.compress.getPressureSwitchValue() == true || pressed3){
            Hardware.compress.stop();
        }
        
        //piston code
        DoubleSolenoid.Value opened = Hardware.piston.get();
        
        if(pressed1 && opened == DoubleSolenoid.Value.kReverse) {
            Hardware.piston.set(DoubleSolenoid.Value.kForward);
        } else if(pressed1 && opened == DoubleSolenoid.Value.kForward) {
           Hardware.piston.set(DoubleSolenoid.Value.kReverse);
        
        }
    
    }
    
}
