package ejercicioDos;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {

	public Main () {
		int numJugadores = 0;
		ArrayList<String> listaNombres = new ArrayList<String>();
		ArrayList<Ventana> ventanas = new ArrayList<Ventana>();
		
		int posicionX = 10;
		int posicionY = 10;
		
		numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Numero de miembros en chat"));
		
		for (int i = 0; i < numJugadores; i++) {
			listaNombres.add(JOptionPane.showInputDialog("Nombre del miembro " + (i + 1)));
		}
		
		Buffer buffer = new Buffer();
		
		for (int i = 0; i < numJugadores; i++) {
			posicionX = posicionX + 30;
			posicionY = posicionY + 30;
			ventanas.add(new Ventana(posicionX, posicionY, listaNombres.get(i), buffer));
			buffer.addObserver(ventanas.get(i));
		}
		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
	}

}
