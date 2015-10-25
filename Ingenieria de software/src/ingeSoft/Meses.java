package ingeSoft;

import java.util.Scanner;

public class Meses {

	public static void main(String[] args) {
		Meses ejercicio = new Meses();
		ejercicio.calcularDiasDelMes();

	}
	
	public void calcularDiasDelMes(){
		Scanner teclado = new Scanner(System.in);
		int mes;
		
		System.out.println("Introduce el numero de un mes:");
		mes = teclado.nextInt();
		teclado.nextLine();
		
		
		switch (mes) {
		
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			System.out.println("31 dias");
			break;
			
		case 2:
			System.out.println("Depende del año, 28 o 29");
			break;
			
		case 4:
		case 6:
		case 9:
		case 11:
			System.out.println("30 dias");
			break;
			
		default:
			break;
		}
	}
	
	
}
