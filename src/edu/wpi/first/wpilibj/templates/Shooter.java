package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class Shooter extends ScibotThread{
    
    public static final int SHOOT_TIME = 1; //Time, in seconds, it takes to shoot
    public static final int SHOOT_MODE = 1; //Mode for shooting
    public static final int CATCH_MODE = 2; //Mode for cathing
    public static int mode = SHOOT_MODE; //Sets mode to shoot mode right away
    static final double PISTON_DOWN_TIME = 2; //Time it takes to set piston down
    static final double LATCH_TIME = 0.5; //Time it takes to open/close gate latch
    static final double PISTON_RETRACT_TIME = 1; /
    static final double PISTON_WAIT_TIME = 0.2;
    static final double CATCH_MODE_PISTON_TIME = 2; //Time it takes to catch and retract?
    static final double CHARGE_TIME = 2.5; //Time to charge piston
    static final double SWING_TIME = 2; //Time it takes for claw to swing upwards?
    
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
        if(Hardware.rightJoy.getTrigger()) { //Checks to see if trigger is pressed
            if(mode == SHOOT_MODE) { //Checks to see if shoot mode is enabled
                shoot(); //Shoots ball
                withdraw(); //Withdraws claw
            }
            else if(mode == CATCH_MODE) { //Checks to see if catch mode is enabled
                catchBall(); //catches ball
            }
            
        }else {
            //prepare();
        }
    }
    
    //fling- charge piston, open claw, release latch
    public static void shoot(){// might need to split this up
        Hardware.piston.set(kForward); //Sets main piston to forward solenoid value 
        Timer.delay(CHARGE_TIME); //Delay of 2.5 sec to charge piston
        Hardware.gateLatch.set(kReverse); //Releases latch
        Timer.delay(SWING_TIME);  //Delay of 2 sec
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
        Hardware.gateLatch.set(kReverse); //Releases gate latch
        Hardware.piston.set(kForward); //Raise claw
        Timer.delay(CATCH_MODE_PISTON_TIME); //Delay for catching time
        Hardware.piston.set(kOff); //Lowers claw
        Hardware.gateLatch.set(kOff); //Closes latch
    }
    
    //Button changes mode
    //FIXME this method is never used
    public static void changeMode() {
        if(mode == SHOOT_MODE) { //Checks to see if shoot mode is enabled (which it is at first)
            mode = CATCH_MODE; //Changes mode from shoot to catch
            withdraw(); 
            //            prepare(); should be handled by function loop
        }
        else {
            mode = SHOOT_MODE; //changes mode from catch to shoot
            //withdraw(); should already been in rest position
        }
    }
    
}

