package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author meydany, luzono
 */
public class GyroClass{
    
    double angle;
    
    public void startup(){
        Hardware.gyro.reset();
    }
    
    protected void iteration(){
        angle = Hardware.gyro.getAngle();
        
        while (angle>=360){
            angle % 360 = angle;
        }
     
    }
    
    public double returnAngle(){
        return angle;
    }
}
