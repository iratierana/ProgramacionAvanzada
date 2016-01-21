

public abstract class Artefacto {
	int id;
	String nombre;
	
	public Artefacto(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public abstract int getVidas();
	abstract void activar (Criatura c);
	@Override
	public String toString() {
		return "Artefacto [id=" + id + ", nombre=" + nombre + "]";
	}
}
