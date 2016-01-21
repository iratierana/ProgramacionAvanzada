package proba1;

public class Paciente extends Usuario{
	TablaEjercicios ejercicios;

	public Paciente(String userName, String nombre, String apellido1, String apellido2, String centro,
			TablaEjercicios ejercicios) {
		super(userName, nombre, apellido1, apellido2, centro);
		this.ejercicios = ejercicios;
	}

	public Paciente(String userName) {
		super(userName);
		this.ejercicios = getEjercicios();
	}

	public TablaEjercicios getEjercicios() {
		if (ejercicios == null)
			ejercicios = cargarEjercicios();

		return ejercicios;
	}

	private TablaEjercicios cargarEjercicios() {
		// Leer desde fichero aqui
		return new TablaEjercicios();
	}

	public void setEjercicios(TablaEjercicios ejercicios) {
		 this.ejercicios = ejercicios;
	}


}
