package ariketa1;

import java.util.ArrayList;

public class Alumno {

	String nombre;
	String apellido1;
	String apellido2;
	
	
	
	ArrayList<Asignatura> listaAsignatura;


	public Alumno(String nombre,  String apellido1, String apellido2) {
		nombre = this.nombre;
		apellido1 = this.apellido1;
		apellido2 = this.apellido2;
		listaAsignatura = new ArrayList<Asignatura>();
	}

	public String toString(){
		return nombre + " " + apellido1 + " " + apellido2;
	}

	
}
