


public class Elfo extends Criatura {
	final int NUMVIDASELFO = 15;
	
	
	
	public Elfo(int id, String nombre) {
		super(id, nombre);
		this.estado = 0;
		this.numVidas = NUMVIDASELFO;
	}

	@Override
	public boolean actuar(Criatura c, Artefacto a) {
		System.out.println(this.nombre + " usa "+ a.nombre +" contra "+ c.nombre);
		a.activar(c);
		return true;
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
