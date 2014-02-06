public class Shooter extends SciBotThread{
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
      Hardware.piston.set(true);
      Thread.sleep(100);
      Hardware.relay.setDirrection(Relay.kReverse);
      Hardware.piston.set(false);
      Hardware.relay.stop();
  }
}
