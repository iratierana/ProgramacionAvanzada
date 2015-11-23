package Bolas;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class Demo1  implements Observer {
	
	Pelota pelota;
	Panel panel;
	JFrame jf;
	
	public Demo1(){
		jf = new JFrame("Demo1");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setContentPane(crearPanelVentana());
       
        
        jf.pack();
        jf.setVisible(true);
       
	}
   
	

    private Container crearPanelVentana() {
    	JPanel panel= new JPanel(new BorderLayout());
		
    	 pelota = new Pelota();
         pelota.addObserver(this);
         panel  = new Panel(pelota);
         jf.getContentPane().add(panel);
         
         
		return panel;
	}



	public void dinamizar() throws Exception {
        long tiempoViejo = System.nanoTime();
        float dt = 0.01f;
        while (true) {
         
        	Thread.sleep(10);
        	pelota.mover(dt);
           
        }
    }
    @Override
	public void update(Observable o, Object ob) {
		jf.repaint();
		
	}
    public static void main(String[] args) throws Exception {
    	
       Demo1 juego = new Demo1();
       juego.dinamizar();
        
    }

	
}
