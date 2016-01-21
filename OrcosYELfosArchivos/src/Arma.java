

public class Arma extends Artefacto {
	
	int vidas;	
	public Arma(int id, String nombre,int vidas) {
		super(id, nombre);
		this.vidas = vidas;
	}

	@Override
	void activar(Criatura c) {
		
		c.setNumVidas(c.getNumVidas()-this.vidas);
	}

	@Override
	public String guardarEnTexto(String separador) {
		
		return getClass().getSimpleName() + separador + id + separador + nombre + separador + vidas;
		
	}

	
	

}
