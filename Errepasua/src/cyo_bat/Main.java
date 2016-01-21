package cyo_bat;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int hora1, min1, hora2, min2 = 0;
		int resultado;
		
		System.out.println("Mete la primera hora 'hhmm'");
		hora1 = teclado.nextInt();
		min1 = teclado.nextInt();
		teclado.nextLine();
		
		System.out.println("Mete la segunda hora 'hhmm'");
		hora2 = teclado.nextInt();
		min1 = teclado.nextInt();
		teclado.nextLine();
		
		resultado = calcularDiferencia(hora1, min1, hora2, min2);
		
		System.out.println(resultado +"minutos hay entre las dos horas");		

	}

	private static int calcularDiferencia(int hora1, int min1, int hora2, int min2) {
		int resultado = 0;
		
		resultado = ((hora1 * 60) + min1) - ((hora2 * 60) + min2);
		
		return resultado;
	}

	
	
}
