/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Ultrasonic;

/**
 *
 * @author Yoli
 */
public class UltrasonicSensors {
    
   
    
    public void turnOn() {
        Hardware.sensor.setEnabled(true);
    }
    
    public double getRange(){
        
        if(Hardware.sensor.isEnabled() && Hardware.sensor.isRangeValid()) {
            return Hardware.sensor.getRangeInches();
        } else {
            return 0;
        }
    
    }
    
    public void turnOff() {
        Hardware.sensor.setEnabled(false);
    }
}
