

import java.util.Random;

public class Orco extends Criatura {
	final int NUMVIDASORCO = 10;
	
	Random generador = new Random ();
	
	public Orco(int id, String nombre) {
		super(id, nombre);
		this.estado = 0;
		this.numVidas = NUMVIDASORCO;
	}

	@Override
	public boolean actuar(Criatura c, Artefacto a) {
		boolean ataque = generador.nextBoolean();
		if (ataque){
			System.out.println(this.nombre + " usa "+ a.nombre +" contra "+ c.nombre);
			a.activar(c);
		}else{
			System.out.println(this.nombre + " usa "+ a.nombre +" contra "+ c.nombre+ " pero falla");
		}
		return ataque;
	}

	@Override
	public String guardarEnTexto(String separador) {
		String ret = "";
		ret += getClass().getSimpleName() + separador + id + separador + nombre + separador + numVidas +  separador + estado;
		for(Artefacto a : artefactos){
			ret += separador + a.getId();
		}
		return ret;
	}

	

}
