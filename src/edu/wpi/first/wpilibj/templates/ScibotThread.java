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
public abstract class ScibotThread implements Runnable {
    
    private Thread thread;
    
    public void start() {
      thread = new Thread(this);
      thread.start();
      System.out.println("Thread started");
    }
    
    public void run() {
      while(Thread.currentThread().isAlive()) {
        main();
        //System.out.println("Running");
     	}
    }
    
    public abstract void main();
    
    public void stop() {
    	thread.interrupt();
    }
}
