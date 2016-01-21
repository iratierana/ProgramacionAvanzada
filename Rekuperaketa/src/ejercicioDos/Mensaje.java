package ejercicioDos;

import java.util.Date;

public class Mensaje {

	String autor;
	String mensaje;
	
	public Mensaje(String texto, String autor) {
		this.autor = autor;
		this.mensaje = texto;
	}

	public String getAutor() {
		return autor;
	}

	public String getMensaje() {
		return mensaje;
	}

	
	
}
