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
    
    public Thread thread;
    public boolean running;
    
    public void start() {
      thread = new Thread(this);
      running = true;
      thread.start();
      System.out.println("Thread started");
    }
    
    public void run() {
      while(running) {
          function();
          try {
            Thread.sleep(10);
           }
           catch (InterruptedException e) {}
        //System.out.println("Running");
     	}
    }
    
    public abstract void function();
    
    public void stop() {
    	running = false;
    }
}
