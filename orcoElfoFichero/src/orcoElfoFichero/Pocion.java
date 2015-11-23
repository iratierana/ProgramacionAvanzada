package orcoElfoFichero;

public class Pocion extends Artefacto{

	public Pocion(int vidas, String nombre) {
		super(vidas, nombre);
	}
	public Pocion(String strFichero[]){
		super(Integer.parseInt(strFichero[1]),strFichero[2]);
	}

	@Override
	public String toString() {
		String artefacto = super.toString();
		return "Pocion"+"$"+ artefacto;
	}

	@Override
	public String visualizar() {
		// TODO Auto-generated method stub
		return "Pocion " + super.visualizar();
	}
	@Override
	public void accion(Criatura criaturaObjetivo) {
		criaturaObjetivo.setVida(criaturaObjetivo.getVida() + vidas);
	}

	
}
