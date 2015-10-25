package empresa;

public class Comercial extends Empleado {
	int ventas;

	
	
	public Comercial(int salario, String nombre, int ventas) {
		super(salario, nombre);
		this.ventas = ventas;
	}

	

	@Override
	public String toString() {
		return "Comercial [ventas=" + ventas + "]";
	}
	
	
}
