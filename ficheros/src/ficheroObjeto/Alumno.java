package ficheroObjeto;

import java.io.Serializable;

public class Alumno implements Serializable{

	int ID;
	String nombre;
	String apellido1;
	String apellido2;
	String poblacion;
	int nota;

	public Alumno(int iD, String nombre, String apellido1, String apellido2, String poblacion, int nota){
		super();
		this.ID = iD;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.poblacion = poblacion;
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Alumno [ID=" + ID + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", poblacion=" + poblacion + ", nota=" + nota + "]";
	}
	
}
