public class Shooter extends SciBotThread{
  
  public final int SHOOT_TIME = 100; //Time, in seconds, it takes to shoot
  
  public void function(){
    //Code to turn compressor on and off based on value of pressure sensor
    if(!Hardware.compress.getPressureSwitchValue()) {
      Hardware.compress.start();
    }
    else {
      compress.stop();
    }
      
    if (Hardware.rightJoy.getTrigger()){
       shoot();
    }
  }
  
  public static void shoot(){
    //Code to shoot
      Hardware.relay.set(Relay.kForward);
      Hardware.relay.set(Relay.kOn);
      Hardware.piston1.set(DoubleSolenoid.kForward);
      Hardware.piston2.set(DoubleSolenoid.kForward);
      
      Timer.delay(SHOOT_TIME);
      
      Hardware.relay.stop();
      Hardware.piston1.set(DoubleSolenoid.kReverse);
      Hardware.piston2.set(DoubleSolenoid.kReverse);
      
      Timer.delay(SHOOT_TIME);
      
      Hardware.piston1.set(DoubleSolenoid.kOff);
      Hardware.piston2.set(DoubleSolenoid.kOff);
  }
}
