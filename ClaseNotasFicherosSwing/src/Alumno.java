import java.io.Serializable;

import javax.swing.DefaultListModel;

public class Alumno implements Serializable{

	String nombre;
	String apellido;
	String apellido2;
	DefaultListModel<Asignatura> asignaturas;
	
	public Alumno(String nombre, String apellido, String apellido2){
		this.nombre = nombre;
		this.apellido = apellido;
		this.apellido2 = apellido2;
		asignaturas = new DefaultListModel<>();
	}
	
	public void addAsignatura(Asignatura asig){
		asignaturas.addElement(asig);
	}
	
	public boolean isTodoAprovado(){
		
		for(int i = 0; i < asignaturas.getSize();i++){
			if(!asignaturas.getElementAt(i).isAprovado()) return false; 
		}
		
		return true;
		
	}
	
	public Alumno(String linea, String separator){
		String fields[];
		fields = linea.split(separator);
		this.nombre = fields[0];
		this.apellido = fields[1];
		this.apellido2 = fields[2];
		asignaturas = new DefaultListModel<>();
	}
	
	public String escribirEnArchivo(String separator){
		String s = "";
		s +=nombre + separator + apellido + separator + apellido2;
		for(int i = 0; i < asignaturas.size(); i++){
			s += "\n" + asignaturas.getElementAt(i).escribirEnFichero(separator);
		}
		return s;
	}

	
	
	public DefaultListModel<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(DefaultListModel<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	@Override
	public String toString() {
		return nombre + " " + apellido + " " + apellido2;
	}
	
	

	
}
