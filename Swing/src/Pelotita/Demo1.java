package Pelotita;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;


public class Demo1 extends JComponent {
	private static int ANCHO = 600;
	private static int ALTO = 450;
	private static int DIAMETRO = 20;
	
	private float x,y;
	private float vx, vy;
	
	public Demo1(){
		setPreferredSize(new Dimension(ANCHO, ALTO));
		x = 0;
		y = 0;
		vx = 300;
		vy = 400;
		}
	
	private void fisica(float dt){
		x += vx * dt;
		y += vy * dt;
		
		 if (vx < 0 && x <= 0 || vx > 0 && x + DIAMETRO >= ANCHO)
	            vx = -vx;
	        if (vy < 0 && y < 0 || vy > 0 && y + DIAMETRO >= ALTO)
	            vy = -vy;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, ANCHO, ALTO);
		g.setColor(Color.ORANGE);
		g.fillOval(Math.round(x), Math.round(y), DIAMETRO, DIAMETRO);
		
	}
	private void dibuja() throws Exception {
	    	this.repaint();
	    }
	public void cicloPrincipalJuego() throws Exception {
        float dt = 0.01f;
        while (true) {
        	Thread.sleep(10);
        	fisica(dt);
            dibuja();
        }
    }

    public static void main(String[] args) throws Exception {
    	JFrame jf = new JFrame("Demo1");
    	
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        Demo1 demo1 = new Demo1();
        jf.getContentPane().add(demo1);
        jf.pack();
        jf.setVisible(true);
        demo1.cicloPrincipalJuego();
    }
}
