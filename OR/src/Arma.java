

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
	public int getVidas() {
		// TODO Auto-generated method stub
		return this.vidas;
	}

}
