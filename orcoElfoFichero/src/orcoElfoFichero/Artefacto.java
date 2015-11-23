package orcoElfoFichero;

import java.io.Serializable;

public abstract class Artefacto implements Serializable{

	int vidas;
	String nombre;
	
	public Artefacto(int vidas, String nombre) {
		super();
		this.vidas = vidas;
		this.nombre = nombre;

	}

	@Override
	public String toString() {
		return vidas + "$" + nombre;
	}
	
	public String visualizar(){
		return nombre + ", Vidas: "+vidas;
	}
	
	abstract public void accion(Criatura criaturaObjetivo);
	
}
