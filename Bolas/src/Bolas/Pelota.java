package Bolas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

public class Pelota extends Observable{
	 private final static int DIAMETRO = 20;
	 
	 private float x, y;
	 private float vx, vy;
	 int ancho,alto;
	 
	 public Pelota(){
	 	x = 10;
        y = 20;
        vx = 300;
        vy = 400;
	 }
	 public void mover(float dt) {
	        x += vx * dt;
	        y += vy * dt;
	        
	        if (vx < 0 && x <= 0 || vx > 0 && x + DIAMETRO >= ancho)
	            vx = -vx;
	        if (vy < 0 && y < 0 || vy > 0 && y + DIAMETRO >= alto)
	            vy = -vy;
	        this.setChanged();
	        this.notifyObservers();
	  }
	 
	 public void dibujar(Graphics g){
		 g.setColor(Color.red);
		  g.fillOval(Math.round(x), Math.round(y), DIAMETRO, DIAMETRO);
	 }
	public void setLimites(int ancho, int alto) {
		this.alto = alto;
		this.ancho = ancho;
		
	}
}
