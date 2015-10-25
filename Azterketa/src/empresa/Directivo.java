package empresa;

public class Directivo extends Empleado {

	int productividad;

	
	public Directivo(int salario, String nombre, int productividad) {
		super(salario, nombre);
		this.productividad = productividad;
	}


	


	@Override
	public String toString() {
		return "Directivo [productividad=" + productividad + "]";
	}

	
	
	
}
