package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends ScibotThread{
    
    public static final int SHOOT_TIME = 1; //Time, in seconds, it takes to shoot
    public static final int SHOOT_MODE = 1;
    public static final int CATCH_MODE = 2;
    public static int mode = SHOOT_MODE;
    static final double PISTON_DOWN_TIME = 2;
    static final double LATCH_TIME = 0.5;
    static final double PISTON_RETRACT_TIME = 1;
    static final double PISTON_WAIT_TIME = 0.2;
    static final double CATCH_MODE_PISTON_TIME = 2;
    static final double CHARGE_TIME = 2.5;
    static final double SWING_TIME = 2;
    
    static final Value kForward = DoubleSolenoid.Value.kForward;
    static final Value kReverse = DoubleSolenoid.Value.kReverse;
    static final Value kOff = DoubleSolenoid.Value.kOff;
    
    public void function(){
        //Code to turn compressor on and off based on value of pressure sensor
//        if(!Hardware.compressor.getPressureSwitchValue()) {
//            Hardware.compressor.start();
//        }
//        else {
//            Hardware.compressor.stop();
//        }
        
        //Code to shoot
        if(Hardware.rightJoy.getTrigger()) {
            if(mode == SHOOT_MODE) {
                shoot();
                withdraw();
            }
            else if(mode == CATCH_MODE) {
                catchBall();
            }
            
        }else {
            //prepare();
        }
    }
    
    //fling- charge piston, open claw, release latch
    public static void shoot(){// might need to split this up
        Hardware.piston.set(kForward);
        Timer.delay(CHARGE_TIME);
        Hardware.gateLatch.set(kReverse);
        Timer.delay(SWING_TIME);
    }
    
    //retract- retract piston, close latch, close claw
    public static void withdraw() {
        //withdraw piston
        Hardware.piston.set(kReverse);
        Timer.delay(PISTON_DOWN_TIME);
        //close latch
        Hardware.gateLatch.set(kForward);
        
        Timer.delay(LATCH_TIME); //same time is used for gatelatch
//        Hardware.gateLatch.set(kOff);
    }
    
    public static void prepare() {
//        Hardware.clawMotor.set(0.7);
        //        if(mode == SHOOT_MODE) {
        Hardware.piston.set(kForward);
        //        }
        //        else if(mode == CATCH_MODE) {
        //
        //        }
    }
    
    public static void catchBall() {
        Hardware.gateLatch.set(kReverse);
        Hardware.piston.set(kForward);
        Timer.delay(CATCH_MODE_PISTON_TIME);
        Hardware.piston.set(kOff);
        Hardware.gateLatch.set(kOff);
    }
    
    //Button changes mode
    //FIXME this method is never used
    public static void changeMode() {
        if(mode == SHOOT_MODE) {
            mode = CATCH_MODE;
            withdraw();
            //            prepare(); should be handled by function loop
        }
        else {
            mode = SHOOT_MODE;
            //withdraw(); should already been in rest position
        }
    }
    
}

