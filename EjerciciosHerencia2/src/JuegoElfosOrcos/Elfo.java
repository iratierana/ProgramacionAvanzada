package JuegoElfosOrcos;

public class Elfo extends Criatura {

	public Elfo(int vida, String nombre, Artefacto artefacto) {
		super(vida, nombre, artefacto);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Elfo [vida=" + vida + ", nombre=" + nombre + ", artefacto="
				+ artefacto.toString() + "]";
	}

	@Override
	public void accion(Criatura criaturaObjetivo) {
		artefacto.accion(criaturaObjetivo);
		System.out.println(nombre+"ha acertado a "+ criaturaObjetivo.getNombre());
	}
	

	

	
}
