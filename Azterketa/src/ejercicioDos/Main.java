package ejercicioDos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	ArrayList<Integer> numeros;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	private void miMain() {
		Random randon = new Random();
		int numero = 0;
		numeros = new ArrayList<Integer>();
		Scanner teclado = new Scanner(System.in);

		

		Generador generador = new Generador(numero);

		int opcion = -1;

		do {
			
			System.out.println("**** Con que quieres trabajar? ****");
			System.out.println("1-.Numeros capicuas");
			System.out.println("2-.Numeros primos");
			System.out.println("3-.Numeros cuyas cifras sumen 13");
			System.out.println("4-.Salir");
			
			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				int cont = 0;

				do {
					
					numero = randon.nextInt(1000);
					generador.numeroCapicua();;
					cont++;
				} while (cont < 10);
				break;
			case 2:
				int contPri = 0;

				do {
					
					numero = randon.nextInt(1000);
					generador.numeroPrimo();
					contPri++;
				} while (contPri < 10);
				break;
			case 3:
				int cont13 = 0;

				do {
					
					numero = randon.nextInt(1000);
					generador.numeroSuma();
					cont13++;
				} while (cont13 < 10);
				break;

			default:
				break;
			}
		} while (opcion != 0);

	}

	

}