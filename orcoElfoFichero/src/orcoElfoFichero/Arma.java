package orcoElfoFichero;

public class Arma extends Artefacto{

	public Arma(int vidas, String nombre) {
		super(vidas, nombre);
	}
	public Arma(String strFichero[]){
		super(Integer.parseInt(strFichero[1]),strFichero[2]);
	}

	@Override
	public String toString() {
		String artefacto = super.toString();
		return "Arma"+"$"+artefacto;
	}

	@Override
	public String visualizar() {
		// TODO Auto-generated method stub
		return "Arma " + super.visualizar();
	}

	@Override
	public void accion(Criatura criaturaObjetivo) {
		criaturaObjetivo.setVida(criaturaObjetivo.getVida() - vidas);
	}
	
	
	

}
