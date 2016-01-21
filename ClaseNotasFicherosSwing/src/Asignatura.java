import java.io.Serializable;

public class Asignatura implements Serializable{
	
	double nota;
	String nombre;
	
	public Asignatura(String nombre, double nota){
		this.nombre = nombre;
		this.nota = nota;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean isAprovado(){
		return nota >= 5;
	}
	
	public Asignatura(String linea, String separator){
		String fields[];
		fields = linea.split(separator);
		this.nombre = fields[0];
		this.nota = Double.parseDouble(fields[1]);
	}
	
	public String escribirEnFichero(String separator){
		return nombre + separator + nota;
	}
	
	@Override
	public String toString() {
		return nombre + ":  " + nota;
	}
	

}
