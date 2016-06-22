package clases;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * @author manu_
 */
public class Progreso implements ActionListener{
    
    public Timer timer;
    
    
    
    public boolean actionPerformed(ActionEvent evt, JProgressBar bar){
  
        int n=bar.getValue();
        if(n<100){
            n++;
            bar.setValue(n);
        }
        else{
            timer.stop();
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void empezar(){
        timer = new Timer(30, new Progreso());
    }

    
}
