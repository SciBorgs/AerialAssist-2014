package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends ScibotThread{
  
  public static final int SHOOT_TIME = 100; //Time, in seconds, it takes to shoot
  public static boolean isShootingMode;
  
  public void function(){
    //Code to turn compressoror on and off based on value of pressure sensor
    if(!Hardware.compressor.getPressureSwitchValue()) {
      Hardware.compressor.start();
    }
    else {
      Hardware.compressor.stop();
    }
    
    //Shoots based on trigger and mode
    if (Hardware.rightJoy.getTrigger() && isShootingMode){
       shoot();
    }
    else {
       catchBall();  
    }
    
    //Switches modes if neccesary
    if (Hardware.rightJoy.getRawButton(10)) { //Alter button
        isShootingMode = !isShootingMode;
        if(isShootingMode) {
            
        }
    }
  }
  
  public static void shoot(){
    //Code to shoot
      Hardware.relay.set(Relay.Value.kForward);
      Hardware.relay.set(Relay.Value.kOn);
      Hardware.piston1.set(DoubleSolenoid.Value.kForward);
      Hardware.piston2.set(DoubleSolenoid.Value.kForward);
      
      Timer.delay(SHOOT_TIME);
      
      Hardware.relay.set(Relay.Value.kOff);
      Hardware.piston1.set(DoubleSolenoid.Value.kReverse);
      Hardware.piston2.set(DoubleSolenoid.Value.kReverse);
      
      Timer.delay(SHOOT_TIME);
      
      Hardware.piston1.set(DoubleSolenoid.Value.kOff);
      Hardware.piston2.set(DoubleSolenoid.Value.kOff);
  }
  
  public static void catchBall() {
      
  }
}
