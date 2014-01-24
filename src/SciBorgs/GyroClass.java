package SciBorgs;

/**
 *
 * @author meydany, luzono
 */
public class GyroClass{
    
    Hardware hardware = new Hardware();
    double angle;
    
    public void startup(){
        hardware.direction.reset();
    }
    
    protected void iteration(){
        angle = hardware.direction.getAngle();
        
        while (angle>=360){
            angle -= 360;
        }
     
    }
    
    public double returnAngle(){
        return angle;
    }
}
