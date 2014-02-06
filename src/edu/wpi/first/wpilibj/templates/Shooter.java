public class Shooter extends SciBotThread{
  
  public final int SHOOT_TIME = 100; //Time, in milliseconds, it takes to shoot
  
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
      
      try {
        Thread.sleep(SHOOT_TIME);
      } catch(InterruptedException e) {}
      
      Hardware.relay.stop();
      Hardware.piston1.set(DoubleSolenoid.kReverse);
      Hardware.piston2.set(DoubleSolenoid.kReverse);
      
      try {
        Thread.sleep(SHOOT_TIME);
      } catch(InterruptedException e) {}
      
      Hardware.piston1.set(DoubleSolenoid.kOff);
      Hardware.piston2.set(DoubleSolenoid.kOff);
  }
}
