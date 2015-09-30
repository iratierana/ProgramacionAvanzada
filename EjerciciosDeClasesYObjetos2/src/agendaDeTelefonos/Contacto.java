package agendaDeTelefonos;

public class Contacto {
	long numero;
	String nombre;
	
	public Contacto(long numero, String nombre) {
		super();
		this.numero = numero;
		this.nombre = nombre;
	}

	public boolean comprobar(String nombre){
		if(this.nombre.toLowerCase().equals(nombre.toLowerCase())){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Contacto [numero=" + numero + ", nombre=" + nombre + "]";
	}
			
	
}
