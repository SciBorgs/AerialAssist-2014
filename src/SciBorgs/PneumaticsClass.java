/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SciBorgs;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Yoli
 */
public class PneumaticsClass {
    
   Hardware hardware = new Hardware();
    
    //Button for piston
   boolean pressed1 = hardware.rightjoy.getRawButton(3);
    
    public void iteration() {
        
        //piston code
        DoubleSolenoid.Value opened = hardware.piston.get();
        
        if(pressed1 && opened == DoubleSolenoid.Value.kReverse) {
            hardware.piston.set(DoubleSolenoid.Value.kForward);
        } else if(pressed1 && opened == DoubleSolenoid.Value.kForward) {
           hardware.piston.set(DoubleSolenoid.Value.kReverse);
        
        }
    
    }
    
}
