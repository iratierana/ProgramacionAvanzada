package orcoElfoFichero;

import java.io.Serializable;

public abstract class Criatura implements Serializable{

	int vida;
	String nombre;
	Artefacto artefacto;
	
	public Criatura(int vida, String nombre, Artefacto artefacto) {
		super();
		this.vida = vida;
		this.nombre = nombre;
		this.artefacto = artefacto;
	}

	@Override
	public String toString() {
		return "Criatura [vida=" + vida + ", nombre=" + nombre + ", artefacto="
				+ artefacto.toString() + "]";
	}
	
	public abstract void accion(Criatura criaturaObjetivo);

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
