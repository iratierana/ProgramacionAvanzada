package ejercicioUno;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ListaDispositivos extends ArrayList<Dispositivo> {

	final String FICHERO = "dispositivos.txt";
	
	public ListaDispositivos(){
		this.inicializarLista(FICHERO);
	}

	private void inicializarLista(String nombreFichero) {
		BufferedReader in =null;
		char tipo = 0;
		String linea = null;
		String datos[] = null;
		
		try {
			in = new BufferedReader(new FileReader(nombreFichero));
			while ((linea=in.readLine())!=null){
				if(tipo == 'I'){
					datos = linea.split("[$]");
					this.add(new Dispositivo(Integer.parseInt(datos[0]), datos[1], datos[2]));
				}
				
				else if(tipo == 'R'){
					datos = linea.split("[$]");
					this.add(new Dispositivo(Integer.parseInt(datos[0]), datos[1], datos[2],Integer.parseInt(datos[3])));
				}
				else if(tipo == 'P'){
					datos = linea.split("[$]");
					this.add(new Dispositivo(Integer.parseInt(datos[0]), datos[1], datos[2],Integer.parseInt(datos[3]), datos[4], datos[5],  datos[6],  datos[7]));

				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Ez");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!=null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		
	}



}
