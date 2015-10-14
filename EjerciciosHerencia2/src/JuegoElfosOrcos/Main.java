package JuegoElfosOrcos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	ArrayList<Criatura> criaturas;
	ArrayList<Artefacto> artefactos;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();
	}

	private void miMain() {
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;

		criaturas = new ArrayList<Criatura>();
		artefactos = new ArrayList<Artefacto>();

		do {
			System.out.println("1-Crear artefacto");
			System.out.println("2-Crear criatura");
			System.out.println("3-Ver criaturas");
			System.out.println("4-Jugar");
			System.out.println("0-Salir");

			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				crearArtefacto();
				break;
			case 2:
				crearCriatura();
				break;
			case 3:
				verCriaturas();
				break;
			case 4:
				jugar();
				break;
			default:
				break;
			}

		} while (opcion != 0);
	}

	private void crearArtefacto() {
		Scanner teclado = new Scanner(System.in);
		String artefacto;

		System.out.println("Tipo artefacto (arma/veneno/pocion):");
		artefacto = teclado.nextLine();

		switch (artefacto.toLowerCase()) {
		case "arma":
			String nombre;
			int vidas;

			System.out.println("Nombre:");
			nombre = teclado.nextLine();
			System.out.println("Cuantas vidas quita?");
			vidas = teclado.nextInt();
			teclado.nextLine();

			Arma arma = new Arma(vidas, nombre);
			artefactos.add(arma);
			break;
		case "veneno":
			String nombreVe;
			int vidasVe;

			System.out.println("Nombre:");
			nombreVe = teclado.nextLine();
			System.out.println("Cuantas vidas quita?");
			vidasVe = teclado.nextInt();
			teclado.nextLine();

			Veneno veneno = new Veneno(vidasVe, nombreVe);
			artefactos.add(veneno);
			break;
		case "pocion":
			String nombrePo;
			int vidasPo;

			System.out.println("Nombre:");
			nombrePo = teclado.nextLine();
			System.out.println("Cuantas vidas da?");
			vidasPo = teclado.nextInt();
			teclado.nextLine();

			Pocion pocion = new Pocion(vidasPo, nombrePo);
			artefactos.add(pocion);
			break;

		default:
			System.out.println("Ese arma no existe");
			break;
		}

	}

	private void crearCriatura() {
		Scanner teclado = new Scanner(System.in);
		String criatura;

		System.out.println("Tipo criatura (elfo/orco):");
		criatura = teclado.nextLine();

		switch (criatura.toLowerCase()) {
		case "elfo":
			String nombre;
			int vidas;
			int numero;

			System.out.println("Nombre?");
			nombre = teclado.nextLine();
			System.out.println("Vidas?");
			vidas = teclado.nextInt();

			for (int i = 0; i < artefactos.size(); i++) {
				System.out.println(i + ".- " + artefactos.get(i).toString());
			}
			System.out.println("Cual?");
			numero = teclado.nextInt();

			Elfo elfo = new Elfo(vidas, nombre, artefactos.get(numero));
			criaturas.add(elfo);
			artefactos.remove(numero);
			break;
		case "orco":
			String nombreOr;
			int vidasOr;
			int numeroOr;

			System.out.println("Nombre?");
			nombreOr = teclado.nextLine();
			System.out.println("Vidas?");
			vidasOr = teclado.nextInt();

			for (int i = 0; i < artefactos.size(); i++) {
				System.out.println(i + ".- " + artefactos.get(i).toString());
			}
			System.out.println("Cual?");
			numeroOr = teclado.nextInt();

			Orco orco = new Orco(vidasOr, nombreOr, artefactos.get(numeroOr));
			criaturas.add(orco);
			artefactos.remove(numeroOr);
			break;

		default:
			break;
		}

	}

	private void verCriaturas() {
		for (int i = 0; i < criaturas.size(); i++) {
			System.out.println(criaturas.get(i).toString());
		}
	}

	private void jugar() {
		Scanner teclado = new Scanner(System.in);
		String opcion = null;
		int idMueve, idObjetivo;
		
		do {
			verCriaturas();
			System.out.println("Id de criatura que mueve");
			idMueve = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Id de criatura objetivo");
			idObjetivo = teclado.nextInt();
			teclado.nextLine();
			
			criaturas.get(idMueve).accion(criaturas.get(idObjetivo));
			
			System.out.println("Mas? (s/n)");
			opcion = teclado.nextLine();
		} while (opcion.toLowerCase().equals("s"));
	}

}
