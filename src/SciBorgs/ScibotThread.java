/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Sophomores
 */
public class ScibotThread implements Runnable {
    
    private Thread thread;
    
    public void start() {
      thread = new Thread(this);
      thread.start();
    }
    
    public void run() {
      while(!Thread.currentThread.isInterrupted()) {
        main();
        try {
          Thread.sleep(10);
        }
        catch (InterruptedException e) {}
     	}
    }
    
    public void main() {
    	//Override this in whatever class extends ScibotThread
    }
    
    public void stop() {
    	thread.interupt();
    }
}
