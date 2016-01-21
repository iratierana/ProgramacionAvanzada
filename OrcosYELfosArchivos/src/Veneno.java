

public class Veneno extends Artefacto {
	
	public Veneno(int id, String nombre) {
		super(id, nombre);
		
	}

	@Override
	void activar(Criatura c) {
		c.setEstado(1);
	}

	@Override
	public String guardarEnTexto(String separador) {
		return getClass().getSimpleName() + separador + id + separador + nombre;
	}

	
	
}
