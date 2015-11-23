package figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo extends Forma {
	private int radio;
	private String descripcion;
	public Circulo(int x, int y,int radio,String descripcion) {
		super(x, y);
		this.descripcion = descripcion;
		this.radio = radio;
	}


	@Override
	public String toString() {
		return "Circulo: "+descripcion; 
	}


	@Override
	protected double calcularArea() {
		double ret = 3.1415*(double)Math.pow(radio, 2);
		return ret;
	}


	@Override
	public void paint(Graphics g) {
	g.setColor(Color.red);
	g.fillOval(getX(), getY(), radio, radio);
		
	}

}
