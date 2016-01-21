package cumpleañosViernes;

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
		for (int año = 1995 ; año <2150 ; año++) {
			LocalDate fecha = LocalDate.of(año, 11, 05);
			if(fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyy").withLocale(Locale.getDefault());
				System.out.println(fecha.format(format));
			}
			
		}
	}

}
