package claseFecha;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Scanner;

public class Domingo {
	Scanner scan = new Scanner(System.in);

	public void miMain(){
		
		
		System.out.print("Introduce tu dia y el mes (dd-mm): ");
		String fecha[] = scan.nextLine().split("[-]");
		int dia = Integer.parseInt(fecha[0]);
		int mes = Integer.parseInt(fecha[1]);
		System.out.print("Que dia de la semana quieres saber (texto)? ");
		String day = scan.nextLine().toLowerCase();
		System.out.print("Para cuantos a�os? ");
		int anos = scan.nextInt(); scan.nextLine();
		LocalDate cumple;
		Instant inicio = Instant.now();
		DayOfWeek dia2 = queDia(day);
		for(int i = LocalDate.now().getYear(); i < LocalDate.now().getYear() + anos; i++){
			cumple = LocalDate.of(i, mes, dia);
			if (cumple.getDayOfWeek().equals(dia2)){
				System.out.printf("El a�o %d cae ", i);
				System.out.println(day);
			}
		}
		System.out.println("Tard� " + Duration.between(inicio, Instant.now()).toMillis() + " ms");
		
		
	}
	
	private DayOfWeek queDia(String dia) {
		DayOfWeek day = null;
		switch(dia){
		case "lunes":
			day = DayOfWeek.MONDAY;
			break;
		case "martes":
			day = DayOfWeek.TUESDAY;
			break;
		case "miercoles":
			day = DayOfWeek.WEDNESDAY;
			break;
		case "jueves":
			day = DayOfWeek.THURSDAY;
			break;
		case "viernes":
			day = DayOfWeek.FRIDAY;
			break;
		case "sabado":
			day = DayOfWeek.SATURDAY;
			break;
		default:
			day = DayOfWeek.SUNDAY;	
		}
		return day;
	}

	public static void main (String[] args){
		Domingo dia = new Domingo();
		dia.miMain();
		
	}
	
	
}
