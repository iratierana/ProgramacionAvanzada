package orcoElfoFichero;

public class Veneno extends Artefacto{

	public Veneno(int vidas, String nombre) {
		super(vidas, nombre);
	}
		// TODO Auto-generated constructor stub
	public Veneno(String strFichero[]){
		super(Integer.parseInt(strFichero[1]),strFichero[2]);
	}
	
	@Override
	public String toString() {
		String artefacto = super.toString();
		return "Veneno"+"$"+artefacto;
	}

	@Override
	public String visualizar() {
		// TODO Auto-generated method stub
		return "Veneno " + super.visualizar();
	}
	@Override
	public void accion(Criatura criaturaObjetivo) {
		criaturaObjetivo.setVida(criaturaObjetivo.getVida() - vidas);
	}

}


