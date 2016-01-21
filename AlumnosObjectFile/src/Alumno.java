import java.io.Serializable;

@SuppressWarnings("serial")
public class Alumno implements Serializable{
	
	int id;
	String nombre;
	String apellido;
	Double nota;
	String ciudad;
	private static int num = 1; 
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumno(String nombre, String apellido, Double nota, String ciudad){
		this.nombre = nombre;
		this.apellido = apellido;
		this.nota = nota;
		this.ciudad = ciudad;
		id = num;
		num++;
	}

	@Override
	public String toString() {
		
		return id + ". " + nombre + " " + apellido + " Nota: " + nota + " Ciudad: " + ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public Class<?> getFieldClass(int indice){
		switch (indice){
		case 0: return Integer.class;
		case 2: return Double.class;
		default: return String.class; 
		}
		
	}

	public static int getNum() {
		return num;
	}

	public static void setNum(int num) {
		Alumno.num = num;
	}

	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return id;
		case 1: return nombre;
		case 2: return apellido;
		case 3: return nota;
		case 4: return ciudad;
		default: return null; 
		}
		
	}
	
	
	
	
	
	

}
