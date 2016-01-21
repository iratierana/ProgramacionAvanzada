package popbl3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;


public class ModeloEjercicioEstandar extends DefaultListModel<Ejercicio> {
	
	final String ficheroEjercicios = "files/ejercicios.txt";
	final String ficheroLesiones = "files/lesiones.txt";
	final String SEPARADOR = "[$]";
	final String SEPARADOR_NUMEROS = "[#]";
	boolean inicializado;
	
	public ModeloEjercicioEstandar(String tipoLesion){
		super();
		if(tipoLesion != null){
			iniciar(tipoLesion);
			inicializado = true;
		}else{
			inicializado = false;
		}
		
	}

	private void iniciar(String tipoLesion) {
		BufferedReader in = null;
		String linea = null;
		String[] valoresEjercicio = null;
		
		String[] ejerciciosAHacer = null;
		ArrayList<String> ejercicios = new ArrayList<>();
		
		try {
			in = new BufferedReader(new FileReader(ficheroLesiones));
			while((linea = in.readLine()) != null){
				valoresEjercicio = linea.split(SEPARADOR);
				if(valoresEjercicio[0].equals(tipoLesion)){
					ejerciciosAHacer = valoresEjercicio[1].split(SEPARADOR_NUMEROS);
					for(String ejercicio : ejerciciosAHacer){
						ejercicios.add(ejercicio);
					}
					break;
				}
				
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close();} catch (IOException e) {}
			}
		
		}
		
		try {
			in = new BufferedReader(new FileReader(ficheroEjercicios));
			while((linea = in.readLine()) != null){
				valoresEjercicio = linea.split(SEPARADOR);
				if(ejercicios.contains(valoresEjercicio[0])){
					Ejercicio nuevo = new Ejercicio(valoresEjercicio[0], valoresEjercicio[1],
													valoresEjercicio[2], valoresEjercicio[3]);
					this.addElement(nuevo);
				}
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close();} catch (IOException e) {}
			}
		
		}
		
	}
	
	public boolean isInicializado(){
		return inicializado;
	}
}
