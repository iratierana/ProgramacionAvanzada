import java.util.ArrayList;


public class Alumno {
	
	String nombre, apellido1, apellido2;
	ArrayList<ResultadoAprendizaje> notas=null;
	
	public Alumno(String nombre, String apellido1, String apellido2, ArrayList<ResultadoAprendizaje> notas){
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.notas=notas;
		
	}
	
	public boolean tieneTodosAprobados() {
		boolean aprobado = true;
		for (int i = 0; i<notas.size(); i++){
			float nota = notas.get(i).getNota();
			if (nota<5.0){
				aprobado = false;
				break;
			}
		}
		return aprobado;
	}

	@Override
	public String toString() {
		return this.nombre+" "+this.apellido1+" "+this.apellido2;
	}

	
	

}
