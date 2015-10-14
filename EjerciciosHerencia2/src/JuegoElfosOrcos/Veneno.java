package JuegoElfosOrcos;

public class Veneno extends Artefacto{

	public Veneno(int vidas, String nombre) {
		super(vidas, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Veneno [vidas=" + vidas + ", nombre=" + nombre+"]";
	}

	@Override
	public void accion(Criatura criaturaObjetivo) {
		criaturaObjetivo.setVida(criaturaObjetivo.getVida() - vidas);
	}

}
