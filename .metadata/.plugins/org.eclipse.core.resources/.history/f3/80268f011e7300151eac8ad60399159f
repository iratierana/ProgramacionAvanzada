package cuatroEnRaya;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	Tablero tablero;
	final static int FILAS = 6;
	final static int COLUMNAS = 7;
	String jugador1;
	String jugador2;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();
	}

	private void miMain() {
		Scanner teclado = new Scanner(System.in);
		tablero = new Tablero(FILAS, COLUMNAS);

		System.out.println("Bienvenidos al 4 en raya");
		System.out.println("Nombre jugador 1(azul):");
		jugador1 = teclado.nextLine();
		System.out.println("Nombre jugador 2(rojo):");
		jugador2 = teclado.nextLine();

		jugar();
	}

	private void jugar() {
		Scanner teclado = new Scanner(System.in);
		int x, y, ronda = 0;

		do {
			ronda++;
			System.out.println("Jugador " + ronda % 2
					+ ", donde quieres colocar la ficha? (f c)");
			x = teclado.nextInt();
			y = teclado.nextInt();
			teclado.nextLine();

			if (x < 7 && x > 0|| y < 6 && y > 0) {
				tablero.darValor(x, y, ronda % 2);
			}
		} while (ganado());

		System.out.println("Jugador " + ronda % 2 + " ha ganado en la ronda "
				+ ronda / 2);
	}

	private boolean ganado() {
		ArrayList<Integer> fila;
		
		for(int i = 0 ; i < FILAS ; i++){
			fila = new ArrayList<Integer>();
			for (int j = 0 ; j < COLUMNAS ; j++){
				fila.add(tablero.recibirValor(i, j));
			}
			if(comprobar(fila))return true;	
		}
		
		for(int i = 0 ; i < COLUMNAS ; i++){
			fila = new ArrayList<Integer>();
			for (int j = 0 ; j < FILAS ; j++){
				fila.add(tablero.recibirValor(i, j));
			}
			if(comprobar(fila))return true;	
		}
		
		for(int i = 0; i < COLUMNAS; i++){
			for(int j = 0; j < FILAS; j++){
				
			}
		}
		
		for(int i = 0 ; i < COLUMNAS ; i++){
			
		}
		return false;
	}
	private boolean comprobar(ArrayList<Integer> fila){
		int contador;
		
		for(int i = 0 ; i < fila.size() ; i ++){
			contador = 0;
			for(int j = i+1 ; j < 4+i ; j++){
				if(fila.get(i)!= fila.get(j)){
					break;
				}
				else{
					contador++;
					if(contador == 4){
						return true;
					}
				}
			}
		}
		
		return false;
		
	}
}
