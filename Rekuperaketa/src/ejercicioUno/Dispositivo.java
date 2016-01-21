package ejercicioUno;

public class Dispositivo {

	String Nombre;
	Boolean  Estado;
	
	
	public Dispositivo(int parseInt, String string, String string2, int parseInt2, String string3, String string4,
			String string5, String string6) {
		// TODO Auto-generated constructor stub
	}
	
	public Dispositivo(int parseInt, String string, String string2) {
		// TODO Auto-generated constructor stub
	}

	public Dispositivo(int parseInt, String string, String string2, int parseInt2) {
		// TODO Auto-generated constructor stub
	}

	public Dispositivo(int parseInt, String string, String string2, int parseInt2, String string3, String string4,
			String string5, String string6, String string7, String string8) {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
	
	@Override
	public String toString() {
		return "Dispositivo [Nombre=" + Nombre + ", Estado=" + Estado + "]";
	}
	
}
