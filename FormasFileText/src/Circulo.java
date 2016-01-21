
public class Circulo extends Forma {

	int radio;

	public Circulo(String nombre, int x, int y, int radio) {
		super(nombre, x, y);
		this.radio = radio;
	}

	@Override
	public boolean impacto(int x, int y) {
		if (radio > Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)))
			return true;
		return false;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	@Override
	public String toString() {
		
		return "Circulo Nombre: " + nombre + " Pos: (" + x + "/" + y + ") Radio: " + radio;
	}
	
}
