import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.AbstractListModel;

@SuppressWarnings("serial")
public class Clase extends AbstractListModel<Alumno> implements Serializable {
	
	String nombre;
	ArrayList<Alumno> lista;
	
	public Clase(String nombre){
		super();
		this.nombre = nombre;
		lista = new ArrayList<>();
	}
	

	@Override
	public Alumno getElementAt(int arg0) {
		return lista.get(arg0);
	}

	@Override
	public int getSize() {
		return lista.size();
	}
	
	public void add(Alumno alumno){
		lista.add(alumno);
	}

	
	public void remove(int index){
		lista.remove(index);
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String escribirEnFichero(String separator){
		String s = "";
		
		s += nombre;
		s += "\n";
		
		for(Alumno a : lista){
			s += "\n" + a.escribirEnArchivo(separator) + "\n";
		}
		
		return s;
	}
	
	

}
