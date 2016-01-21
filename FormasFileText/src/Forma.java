
public abstract class Forma {

	int x, y;
	String nombre;
	
	public Forma(String nombre, int x, int y){
		this.x = x;
		this.y = y;
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public abstract boolean impacto(int x, int y);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
