package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends ScibotThread{
  
  public static final int SHOOT_TIME = 1; //Time, in seconds, it takes to shoot
  public static final int SHOOT_MODE = 1;
  public static final int CATCH_MODE = 2;
  public static int mode;
  
  public void function(){
    //Code to turn compressor on and off based on value of pressure sensor
    if(!Hardware.compressor.getPressureSwitchValue()) {
      Hardware.compressor.start();
    }
    else {
      Hardware.compressor.stop();
    }
    
    //Code to shoot
    if(Hardware.rightJoy.getTrigger()) {
        if(mode == SHOOT_MODE && isReady()) {
            shoot();
        }
        else if(mode == CATCH_MODE) {
            catchBall();
        }
        else {
            prepare();
        }
    }
  }
 
  public static void shoot(){// might need to split this up
      //Code to shoot
      //fling- charge piston, open claw, release latch
      Hardware.piston.set(DoubleSolenoid.Value.kForward);
      Hardware.claw.set(DoubleSolenoid.Value.kForward);
      Timer.delay(1);
      Hardware.gateLatch.set(DoubleSolenoid.Value.kReverse);
      Timer.delay(2);
      
      //retract- retract piston, close latch, close claw
      
//      withdraw();

  }
  
  public static void catchBall() {
      //TODO: make sure it is in shoot position first
  }
  
  public static void prepare() {
      if(mode == SHOOT_MODE) {
        Hardware.piston.set(DoubleSolenoid.Value.kForward);
      }
      else if(mode == CATCH_MODE) {
          /*Move claw to ready position
         
          */
      }
  }
  
  public static boolean isReady() {
      return Hardware.piston.get() == DoubleSolenoid.Value.kForward;// I know I know "what about claw" dwai
 //will add claw later 
  }
  
  //Button changes mode
  public static void changeMode() {
      if(mode == SHOOT_MODE) {
          mode = CATCH_MODE;
          withdraw();
          prepare();
      }
      else {
          mode = SHOOT_MODE;
          withdraw();
      }
  }
  
  public static void withdraw() {
      boolean on = false;
      while(!Hardware.limit.get()){
        if(!on){
            Hardware.piston.set(DoubleSolenoid.Value.kReverse);
            Timer.delay(1);
        }else{
            Hardware.piston.set(DoubleSolenoid.Value.kOff);
            Timer.delay(0.2);
        }
        on = !on;
      }
      Hardware.piston.set(DoubleSolenoid.Value.kOff);
  }
}
  
