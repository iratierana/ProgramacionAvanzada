package popbl3;

import java.util.ArrayList;

public class Paciente extends Usuario {
	
	final String SEPARADOR = "$";
	
	String tipoLesion;
	ArrayList<Ejercicio> ejercicios;
	String fisioAsociado;
	
	public Paciente(String userName, String nombre, String apellido1, String apellido2, String centro,
			ArrayList<Ejercicio> ejercicios) {
		
		super(userName, nombre, apellido1, apellido2, centro);
		this.tipoLesion = "fallo";
		this.ejercicios = ejercicios;
		this.fisioAsociado = "error";
	}
	
	public Paciente(String userName, String nombre, String apellido1, String apellido2, String centro,
			String tipoLesion) {
		
		super(userName, nombre, apellido1, apellido2, centro);
		this.tipoLesion = tipoLesion;
		this.ejercicios = new ArrayList<>();
		this.fisioAsociado = "error";
	}
	
	public Paciente(String userName, String nombre, String apellido1, String apellido2, String centro,
			String tipoLesion, String fisioAsociado) {
		
		super(userName, nombre, apellido1, apellido2, centro);
		this.tipoLesion = tipoLesion;
		this.ejercicios = new ArrayList<>();
		this.fisioAsociado = fisioAsociado;
	}

	public Paciente(String userName, String nombre, String apellido1, String apellido2, String centro,
			String tipoLesion, ArrayList<Ejercicio> ejercicios, String fisioAsociado) {
		
		super(userName, nombre, apellido1, apellido2, centro);
		this.ejercicios = ejercicios;
		this.tipoLesion = tipoLesion;
		this.fisioAsociado = fisioAsociado;
	}
	
	public Paciente(String userName) {
		super(userName);
		this.ejercicios = getEjercicios();
	}
	
	public Paciente(String userName, String tipoLesion) {
		super(userName);
		this.tipoLesion = tipoLesion;
		this.ejercicios = getEjercicios();
	}

	public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
		 this.ejercicios = ejercicios;
	}

	public ArrayList<Ejercicio> getEjercicios() {
		//if (ejercicios == null)
		//	ejercicios = cargarEjercicios();
	
		return ejercicios;
	}

	public void setTipoLesion(String tipoLesion) {
		this.tipoLesion = tipoLesion;
	}

	public String getTipoLesion() {
		return tipoLesion;
	}

	public void setFisioAsociado(String fisioAsociado) {
		this.fisioAsociado = fisioAsociado;
	}

	public String getFisioAsociado() {
		return fisioAsociado;
	}

	@Override
	public String toString() {
		
		return this.getNombre()+" "+this.getApellido1()+" "+this.getApellido2();
	}

	@Override
	public String guardar() {
		
		return super.guardar()+SEPARADOR+tipoLesion+SEPARADOR+fisioAsociado+SEPARADOR+ejercicios.size();
	}

	
	
}
