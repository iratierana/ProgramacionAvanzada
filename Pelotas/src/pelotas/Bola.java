package pelotas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Bola{
	int x,y,radio;
	int vy,vx;
	
	public Bola(int x,int y,int radio) {
		this.x = x;
		this.y = y;
		vy = 2;
		vx = 1;
		this.radio = radio;
	}
	
	public void paint(Graphics arg0) {
		arg0.setColor(Color.RED);
		arg0.fillOval(x, y, radio, radio);
	}
	
}
