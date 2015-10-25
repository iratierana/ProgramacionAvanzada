package empresa;

public class Empleado {

	
	int salario;
	String nombre;
	
	public Empleado(int salario, String nombre) {
		super();
		this.salario = salario;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Empleado [salario=" + salario + ", nombre=" + nombre + ".]".toString();
	}

	
	
}
