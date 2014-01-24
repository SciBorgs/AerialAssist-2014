/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SciBorgs;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 * @author Yoli
 */
public class UltrasonicSensors {
    
    Hardware hardware = new Hardware();
    
    public void turnOn() {
        hardware.sensor.setEnabled(true);
    }
    
    public double getRange(){
        
        if(hardware.sensor.isEnabled() == true && hardware.sensor.isRangeValid() == true) {
            return hardware.sensor.getRangeInches();
        }
        
        return 0;
    }
    
    public void turnOff() {
        hardware.sensor.setEnabled(false);
    }
}
