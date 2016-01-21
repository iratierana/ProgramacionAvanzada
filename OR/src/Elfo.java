

public class Elfo extends Criatura {
	@Override
	public String escribir() {
		
		return "Elfo$" + super.escribir();
		
	}

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

}
