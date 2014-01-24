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
    
    int ping = 1; //Going to change
    int echo = 2; //Going to change
       
    Ultrasonic sensor = new Ultrasonic(ping, echo);
    
    public void turnOn() {
        sensor.setEnabled(true);
    }
    
    public double getRange(){
        
        if(sensor.isEnabled() == true && sensor.isRangeValid() == true) {
            return sensor.getRangeInches();
        }
        
        return 0;
    }
    
    public void turnOff() {
        sensor.setEnabled(false);
    }
}
