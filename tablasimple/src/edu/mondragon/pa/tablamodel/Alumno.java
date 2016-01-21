package edu.mondragon.pa.tablamodel;

public class Alumno {
	final int NUMCAMPOS = 6;
	String nombre,apellido1,apellido2,poblacion;
	int edad;
	boolean sexo;
	
	public Alumno(String n, String a1, String a2, String p, int edad, boolean sex){
		this.nombre=n;
		this.apellido1 = a1;
		this.apellido2 = a2;
		this.poblacion = p;
		this.edad = edad;
		this.sexo = sex;
	}

	public String [] toArray() {
		String [] datos = new String [NUMCAMPOS];
		datos[0] = nombre;
		datos[1] = apellido1;
		datos[2] = apellido2;
		datos[3] = poblacion;
		datos[4] = String.valueOf(edad);
		datos[5] = (sexo)?"H":"M";
		
		return datos;
	}
	
	public Class<?> getFieldClass(int indice){
		switch (indice){
		case 4: return Integer.class;
		case 5: return Boolean.class;
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
		case 5: return new Boolean (sexo);
		default: return null; 
		}
		
	}

	
}
