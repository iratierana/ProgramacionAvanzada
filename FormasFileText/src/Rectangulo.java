
public class Rectangulo extends Forma {

	int ancho;
	int alto;
	
	
	public Rectangulo(String nombre, int x, int y, int ancho, int alto) {
		super(nombre ,x, y);
		this.ancho = ancho;
		this.alto = alto;
	}

	@Override
	public boolean impacto(int x, int y) {
		if(		((x >= this.x) && (x <= this.x + ancho))
				&&
				((y >= this.y) && (y <= this.y + alto))
				) return true;
		return false;
	}

	@Override
	public String toString() {
		
		return "Rectangulo Nombre: " + nombre + " Pos: (" + x + "/" + y + ") Ancho: " + ancho + " Alto: " + alto;
	}
	
	

}
