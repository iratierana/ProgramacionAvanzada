package cumplea�osViernes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		 Main ejercicio = new Main();
		 ejercicio.miMain();
	}
	public void miMain(){
		for (int a�o = 1995 ; a�o <2150 ; a�o++) {
			LocalDate fecha = LocalDate.of(a�o, 11, 05);
			if(fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyy").withLocale(Locale.getDefault());
				System.out.println(fecha.format(format));
			}
			
		}
	}

}
