/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

public class SwitchCase extends ScibotThread {

int button = 0;
//get button
    public void function() {
        for (int i = 1; i >= 13; i++) {
            if (Hardware.rightJoy.getRawButton(i)) {
                button = i;
            } else if(Hardware.rightJoy.getTrigger()){
                button = 13;
            }
        }
        
        //functions
        switch(button){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
            case 12:
                break;
            case 13:
                Shooter.shoot();
                break;
        }
    }
}
