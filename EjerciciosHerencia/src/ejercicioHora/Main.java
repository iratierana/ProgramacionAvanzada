package ejercicioHora;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	Hora hora;
	ArrayList<Hora> horas;
	ListIterator<Hora> horaIt;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.menu();

	}

	public void menu() {
		Scanner teclado = new Scanner(System.in);

		int opcion = 0;

		System.out.println("******Que quiere hacer?******");
		System.out.println("1-.Compara dos horas");
		System.out.println("2-.Comparar mas de dos horas");
		System.out.println("3-.Salir");

		opcion = teclado.nextInt();
		teclado.nextLine();

		switch (opcion) {
		case 1:
			compararDosHoras();
			break;

		case 2:
			compararMasHoras();
			break;
		default:
			break;
		}
	}

	

	public void leerHora(Hora hora) {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Introduce una hora(hh mm)");
		hora.hora = teclado.nextInt();
		hora.minutos = teclado.nextInt();
		teclado.nextLine();

	}

	public void compararDosHoras() {
		Hora hora = new Hora(0, 0);

		leerHora(hora);
		Hora hora1 = new Hora(hora.hora, hora.minutos);

		leerHora(hora);
		Hora hora2 = new Hora(hora.hora, hora.minutos);

		switch (hora1.compararDosHoras(hora2)) {
		case 1:
			System.out.println("La segunda hora que has metido es anterior");
			System.out.println(hora2 + " < " + hora1);
			break;
		case 0:
			System.out.println("Las dos horas son iguales");
			System.out.println(hora2 + " = " + hora1);
			break;
		case -1:
			System.out.println("La primera hora que has metido es anterior");
			System.out.println(hora1 + " > " + hora2);
			break;
		default:
			break;
		}
	}
	public void compararMasHoras() {
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		MiComparador miComparador = new MiComparador();
		
		horas = new ArrayList<Hora>();
		
		do{
			hora = new Hora(0,0);
			leerHora(hora);
			horas.add(hora);
			
			System.out.println("Introducir mas horas?(Si(1)No(0))");
			opcion = teclado.nextInt();
			teclado.nextLine();
		}while(opcion !=0);
		
		Collections.sort(horas, miComparador);
		
		horaIt = horas.listIterator();
		
		while(horaIt.hasNext()){
			System.out.println(horaIt.next().toString());
		}
	}
}
