package ariketa1;

public class Asignatura {


	String nombre;
	String id;
	Integer nota;
	
	public Asignatura(String[] split) {
		nombre = split[0];
		id = split[1];
	}

	public String toString(){
		return nombre + ": " + id;
	}

}
