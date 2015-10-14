package JuegoElfosOrcos;

public class Pocion extends Artefacto{

	public Pocion(int vidas, String nombre) {
		super(vidas, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Pocion [vidas=" + vidas + ", nombre=" + nombre+ "]";
	}

	@Override
	public void accion(Criatura criaturaObjetivo) {
		criaturaObjetivo.setVida(criaturaObjetivo.getVida() + vidas);
	}

	
}
