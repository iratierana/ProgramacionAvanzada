import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListaAlumnos extends DefaultListModel<Alumno> {

	
	public void inicializarListaResultados(String nombreFichero) {
		int kont;
		String linea = null;
		String nombre[] = null;
		String nota[]=null;
		ArrayList<ResultadoAprendizaje> auxNotas;
		BufferedReader in =null;
		try {			
			in = new BufferedReader(new FileReader(nombreFichero));
			while ((linea=in.readLine())!=null){
				nombre = linea.split("[$]");
				kont=0;
				auxNotas=new ArrayList<ResultadoAprendizaje>();
				while (kont!=4){//Es cuatro por que hay cuatro notas diferentes
					linea=in.readLine();
					nota=linea.split("[$]");
					auxNotas.add(new ResultadoAprendizaje(nota[0], Float.valueOf(nota[1])));
					kont++;
				}
				this.addElement(new Alumno(nombre[0],nombre[1], nombre[2], auxNotas));
				auxNotas=null;
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in!=null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		
	}


}
