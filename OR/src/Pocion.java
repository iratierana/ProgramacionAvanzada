

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
	public int getVidas() {
		// TODO Auto-generated method stub
		return this.vidas;
	}

}
