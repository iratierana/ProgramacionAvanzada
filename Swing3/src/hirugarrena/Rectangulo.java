package hirugarrena;
import java.awt.Color;
import java.awt.Graphics;

public class Rectangulo extends Forma {
	private int base,altura;
	private String descripcion;
	public Rectangulo(int x, int y,int base,int altura,String descripcion) {
		super(x, y);
		this.descripcion = descripcion;
		this.base = base;
		this.altura = altura;
	}

	@Override
	public String toString() {
		return "Rectangulo: "+descripcion;  
	}

	@Override
	protected double calcularArea() {
		return base*altura;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), base, altura);
	}

}
