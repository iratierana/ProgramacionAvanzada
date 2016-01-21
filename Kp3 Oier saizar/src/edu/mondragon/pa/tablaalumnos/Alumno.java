package edu.mondragon.pa.tablaalumnos;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Alumno implements Serializable{
	//private static final long serialVersionUID = 1L;
	
	final int NUMCAMPOS = 6;
	String nombre,apellido1,apellido2,poblacion;
	int edad;
	double nota;
	
	public Alumno(String n, String a1, String a2, String p, int edad) {
		this.nombre=n;
		this.apellido1 = a1;
		this.apellido2 = a2;
		this.poblacion = p;
		this.edad = edad;
		this.nota = 0.0;
	}

	
	
	public void setNota(double nota) {
		this.nota = nota;
	}



	public String getNombre() {
		return nombre;
	}



	public String getApellido1() {
		return apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public String getPoblacion() {
		return poblacion;
	}



	public int getEdad() {
		return edad;
	}



	



	public Class<?> getFieldClass(int indice){
		switch (indice){
		case 4: return Integer.class;
		case 5: return Double.class;
		default: return String.class; 
		}
		
	}

	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return nombre;
		case 1: return apellido1;
		case 2: return apellido2;
		case 3: return poblacion;
		case 4: return new Integer(edad);
		case 5: return new Double (nota);
		default: return null; 
		}
		
	}
}
