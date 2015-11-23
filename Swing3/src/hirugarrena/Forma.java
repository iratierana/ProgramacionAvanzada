package hirugarrena;
import java.awt.Canvas;
import java.awt.Graphics;

public abstract class Forma extends Canvas {
	private int cX,cY;
	private double area;	
	
	@Override
	public abstract void paint(Graphics g);

	public Forma(int x,int y) {
		cX=x;
		cY=y;
		
		area=0.0;
	}	
	
	public double getDistancia(int x,int y){
		double dif;
		dif= Math.sqrt(Math.pow(getX()-x,2)+Math.pow(getY()-y, 2));
		return dif;
	}
	
	public double getArea() {
		if( area == 0.0)
			area = calcularArea();
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getX() {
		return cX;
	}

	public int getY() {
		return cY;
	}
	
	@Override
	public abstract String toString();
	
	protected abstract double calcularArea();
	
	

	
	
	
}
