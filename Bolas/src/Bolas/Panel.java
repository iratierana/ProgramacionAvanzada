package Bolas;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private final static int ANCHO = 512;

    private final static int ALTO = 384;

    Pelota pelota;

    
    public Panel(Pelota pelota){
    	 setPreferredSize(new Dimension(ANCHO, ALTO));
    	 this.pelota = pelota;
    	 pelota.setLimites(ANCHO,ALTO);
    }
  
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ANCHO, ALTO);
    
        pelota.dibujar(g);
    }

}
