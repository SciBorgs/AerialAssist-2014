package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends ScibotThread{
  
  public static final int SHOOT_TIME = 100; //Time, in seconds, it takes to shoot
  
  public void function(){
    //Code to turn compressoror on and off based on value of pressure sensor
    if(!Hardware.compressor.getPressureSwitchValue()) {
      Hardware.compressor.start();
    }
    else {
      Hardware.compressor.stop();
    }
    
    //Shoots based on trigger and mode
    if (Hardware.rightJoy.getTrigger() && isShootingMode()){
       shoot();
    }
    else {
       catchBall();  
    }
    
        
    }
 
  public static void shoot(){// might need to split this up
    //Code to shoot
      Hardware.piston.set(DoubleSolenoid.Value.kForward);
      Hardware.claw.set(Relay.Value.kForward);
      
      Timer.delay(1);
      
      Hardware.gateLatch1.set(DoubleSolenoid.Value.kReverse);
      Hardware.gateLatch2.set(DoubleSolenoid.Value.kReverse);
      
      Timer.delay(2);
      
      Hardware.claw.set(Relay.Value.kReverse);
      
      Hardware.gateLatch1.set(DoubleSolenoid.Value.kReverse);
      Hardware.gateLatch2.set(DoubleSolenoid.Value.kReverse);
      
      Timer.delay(SHOOT_TIME);

  }
  
  public static void catchBall() {
      
  }
  public static boolean isShootingMode(){
      return Hardware.piston.get() == DoubleSolenoid.Value.kForward;// I know I know "what about claw" dwai
 //will add claw later 
  }
}
  
