package ejercicioHora;

import java.util.Comparator;

public class MiComparador implements Comparator<Hora>{

	@Override
	public int compare(Hora hora1, Hora hora2) {
		if(hora1.hora < hora2.hora){
			return -1;
		}
		if(hora1.hora == hora2.hora){
			if(hora1.minutos < hora2.minutos){
				return -1;
			}
			if(hora1.minutos == hora2.minutos){
				return 0;
			}
			if(hora1.minutos > hora2.minutos){
				return 1;
			}
		}
		return 1;
	
	}

}
