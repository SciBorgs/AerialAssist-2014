package SciBorgs;


/*
 * StartApplication.java
 *
 */
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

/**
 * State control, component checking, thread management.
 * 
 * @author Pawel, Sophomore
 */
public class Start extends MIDlet {

    protected void startApp() throws MIDletStateChangeException {
        System.out.println("Hello World");
    }

    protected void pauseApp() {
    }

    protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
    }
    
}
