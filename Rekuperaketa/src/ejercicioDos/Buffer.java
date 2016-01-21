package ejercicioDos;

import java.util.ArrayList;
import java.util.Observable;

public class Buffer extends Observable{

	ArrayList<Mensaje> mensajes;
	
	public Buffer () {
		mensajes = new ArrayList<Mensaje>();
	}
	
	public void meterMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
		setChanged();
		notifyObservers();
	}

	public Mensaje recibirUltimo() {
		return mensajes.get(mensajes.size() - 1);
	}
}
