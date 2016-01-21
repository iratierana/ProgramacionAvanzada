package edu.mondragon.pa.listapeliculas;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListaPeliculas extends ArrayList<Pelicula>{
	
	final String FICHPELI="files/peliculas.txt";
	
	public ListaPeliculas() {
		this.inicializarLista(FICHPELI);
	}

	public void inicializarLista(String nombreFichero){	
		BufferedReader in =null;
		String linea = null;
		String datos[] = null;
		
		try {
			in = new BufferedReader(new FileReader(nombreFichero));
			while ((linea=in.readLine())!=null){
				datos = linea.split("[$]");
				this.add(new Pelicula(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3], datos[4], datos[5]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!=null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		
	}

}
