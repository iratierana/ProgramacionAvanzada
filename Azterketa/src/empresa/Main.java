package empresa;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	ArrayList<Empleado> empleados;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	public void miMain() {
		Scanner teclado = new Scanner(System.in);
		int opcion;
		int tipo;
		
		do {

			System.out.println("Que quieres hacer?");
			System.out.println("1-.Dar de alta a empleado");
			System.out.println("2-.Mostrar empleados");
			System.out.println("0-.Salir");

			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				alta();

				break;

			case 2:
				verEmpleado();
				break;
			default:
				break;
			}

		} while (opcion != 0);
	}

	public void verEmpleado() {
		for (int i = 0; i < empleados.size(); i++) {
			System.out.println(empleados.get(i).toString());
		}
		
	}

	public void alta() {
		Scanner teclado = new Scanner(System.in);
		empleados = new ArrayList<Empleado>();

		int tipo;
		System.out.println("1-.Normal");
		System.out.println("2-.Directivo");
		System.out.println("3-.Comercial");

		tipo = teclado.nextInt();
		teclado.nextLine();

		switch (tipo) {
		case 1:
			String nombre;
			int salario;
			
			System.out.println("nombre:");
			nombre = teclado.nextLine();
			System.out.println("salario:");
			salario = teclado.nextInt();
			teclado.hasNextLine();
		
			Normal normal = new Normal(salario, nombre);
			empleados.add(normal);			
			
			break;
		case 2:
			String nombreDi;
			int salarioDi;
			int salarioOf;
			int productividad;
			
			System.out.println("nombre:");
			nombreDi = teclado.nextLine();
			System.out.println("salario:");
			salarioDi = teclado.nextInt();
			teclado.hasNextLine();
			System.out.println("productividad:");
			productividad = teclado.nextInt();
			teclado.hasNextLine();
			
			salarioOf = calcular1(productividad);
			
			Directivo directivo = new Directivo(salarioOf, nombreDi, productividad);
			empleados.add(directivo);	
			break;
		case 3:

			String nombreCo;
			int salarioCo;
			int salOf;
			int ventas;
			
			System.out.println("nombre:");
			nombreCo = teclado.nextLine();
			System.out.println("salario:");
			salarioCo = teclado.nextInt();
			teclado.hasNextLine();
			System.out.println("ventas:");
			ventas = teclado.nextInt();
			teclado.hasNextLine();
			
			salOf = calcular2(ventas);
			
			Comercial comercial = new Comercial(salOf, nombreCo, ventas);
			empleados.add(comercial);	
			break;
		default:
			break;
		}

	}
	public int calcular1(int productividad){
		int salario = 0;
		salario = (int) (salario * (1+productividad/100));
		
		return salario;
	}
	
	public int calcular2(int ventas){
		int salario = 0;
		salario = (int) (salario+ 0.1*ventas);
		
		return salario;
		
	}
		
}
