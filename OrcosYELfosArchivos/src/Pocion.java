

public class Pocion extends Artefacto {
	int vidas;
	
	public Pocion(int id, String nombre, int vidas) {
		super(id, nombre);
		this.vidas = vidas;
	}

	@Override
	void activar(Criatura c) {
		c.setNumVidas (c.getNumVidas()+this.vidas);
		c.setEstado(0);
	}

	@Override
	public String guardarEnTexto(String separador) {
		return getClass().getSimpleName() + separador + id + separador + nombre + separador + vidas;
	}
	
	

}
