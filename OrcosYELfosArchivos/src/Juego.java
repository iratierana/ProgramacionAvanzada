
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Scanner;

public class Juego {

	final String SEPARADOR = "¶";

	ArrayList<Criatura> criaturas;
	ArrayList<Artefacto> artefactos;
	Scanner teclado;

	public Juego() {

		try {
			leerDeFichero();
		} catch (Exception e) {
			artefactos = new ArrayList<>();
			criaturas = new ArrayList<>();
			System.out.println("Se ha producido un error en la lectura de los archivos");
		}

		teclado = new Scanner(System.in);
	}

	private void leerDeFichero() {

		boolean leidosArt = false;
		BufferedReader fileArt = null;
		String linea;
		artefactos = new ArrayList<>();
		try {
			fileArt = new BufferedReader(new FileReader("artefactos.txt"));
			while ((linea = fileArt.readLine()) != null) {
				Artefacto aux = new Arma(1, "a", 1);
				artefactos.add((Artefacto) aux.leerDeTexto(linea, SEPARADOR));
			}
			leidosArt = true;
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo de los artefactos\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			if (fileArt != null)
				try {
					fileArt.close();
				} catch (IOException e) {
			}
		}
		

		BufferedReader fileCri = null;
		criaturas = new ArrayList<>();
		if (leidosArt) {
			try {
				fileCri = new BufferedReader(new FileReader("criaturas.txt"));
				while ((linea = fileCri.readLine()) != null) {
					Criatura aux = new Elfo(1, "a");
					criaturas.add((Criatura) aux.leerDeTexto(linea, SEPARADOR, artefactos));
				}
			} catch (FileNotFoundException e) {
				System.out.println("No se ha encontrado el archivo de las criaturas\n");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} finally {
				if (fileArt != null)
					try {
						fileArt.close();
					} catch (IOException e) {
					}
			}
		}

	}

	public void guardarEnFichero() {
		PrintWriter wrArtefactos = null;
		try {
			wrArtefactos = new PrintWriter(new FileWriter("artefactos.txt"));
			for (Artefacto a : artefactos) {
				wrArtefactos.println(a.guardarEnTexto(SEPARADOR));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wrArtefactos != null) {
				wrArtefactos.close();
			}
		}
		PrintWriter wrCriaturas = null;
		try {
			wrCriaturas = new PrintWriter(new FileWriter("criaturas.txt"));
			for (Criatura a : criaturas) {
				wrCriaturas.println(a.guardarEnTexto(SEPARADOR));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (wrCriaturas != null) {
				wrCriaturas.close();
			}
		}

	}

	public void menu() {
		int opcion = 0;
		do {
			try {

				System.out.println("1.- Crear Artefactos");
				System.out.println("2.- Crear Criaturas");
				System.out.println("3.- Ver Criaturas");
				System.out.println("4.- Jugar");
				System.out.println("0.- Salir");
				System.out.print("Selecciona opcion: ");
				opcion = teclado.nextInt();
				teclado.nextLine();
				switch (opcion) {
				case 1:
					crearArtefactos();
					break;
				case 2:
					crearCriaturas();
					break;
				case 3:
					verCriaturas();
					break;
				case 4:
					jugar();
					break;

				case 0:
					break;
				default:
					System.out.println("opción no válida");
				}

			} catch (InputMismatchException e) {
				System.out.println("Elija una opción indicando su número");
			}
		} while (opcion != 0);
	}

	private void verCriaturas() {
		ListIterator<Criatura> it = criaturas.listIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}

	private void jugar() {
		int idm, idc;
		Criatura mueve, contra;
		Artefacto a;
		String opcion = "S";
		do {
			try {
				System.out.print("Id criatura que mueve: ");
				idm = teclado.nextInt();
				teclado.nextLine();
				mueve = findCriatura(idm);
				a = elegirArtefacto(mueve.getArtefactos());
				System.out.print("Id criatura sobre la que actua");
				idc = teclado.nextInt();
				teclado.nextLine();
				contra = findCriatura(idc);
				mueve.actuar(contra, a);
				System.out.print("Mas (S/N)?");
				opcion = teclado.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("El formato introducido no es correcto");
			} catch (CriaturaNoExisteException e) {
				System.out.println(e.getMessage());
			} catch (CriaturaSinNadaQueUsar e) {
				System.out.println(e.getMessage());
			}
		} while (!opcion.equals("N"));
	}

	private Artefacto elegirArtefacto(ArrayList<Artefacto> artefactos) throws CriaturaSinNadaQueUsar {
		Artefacto artefacto = null;
		int opcion;
		if (artefactos.size() == 0)
			throw new CriaturaSinNadaQueUsar("No hay artefactos");
		if (artefactos.size() == 1)
			return artefactos.get(0);

		do {

			ListIterator<Artefacto> it = artefactos.listIterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}
			try {
				System.out.print("Usa? : ");
				opcion = teclado.nextInt();
				teclado.nextLine();
				artefacto = artefactos.get(opcion);

			} catch (InputMismatchException e) {
				System.out.println("Elija el id adecuado");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} while (artefacto == null);
		return artefacto;
	}

	private ArrayList<Artefacto> elegirArtefactos(ArrayList<Artefacto> artefactos) throws CriaturaSinNadaQueUsar {
		ArrayList<Artefacto> lista = new ArrayList<>();

		Artefacto artefacto = null;
		String opcionSalida;

		if (artefactos.size() == 0)
			throw new CriaturaSinNadaQueUsar("No hay artefactos");
		if (artefactos.size() == 1) {
			lista.add(artefactos.get(0));
			return lista;
		}

		do {

			artefacto = elegirArtefacto(artefactos);

			lista.add(artefacto);
			System.out.print("Quieres añadir más artefactos (s/n)?: ");
			opcionSalida = teclado.nextLine();

		} while (opcionSalida.toLowerCase().equals("s"));
		return lista;
	}

	private Criatura findCriatura(int idc) throws CriaturaNoExisteException {

		Criatura criatura = null;
		ListIterator<Criatura> it = criaturas.listIterator();
		while (it.hasNext()) {
			if ((criatura = it.next()).getId() == idc) {
				return criatura;
			}
		}
		throw new CriaturaNoExisteException("No existe una criatura con ese identificador");

	}

	private void crearCriaturas() {
		String tipo, nombre;
		String opcion = "S";
		Criatura nuevaCriatura;
		int id;
		do {

			System.out.print("Tipo criatura (Elfo/Orco): ");
			tipo = teclado.nextLine();
			System.out.print("Nombre: ");
			nombre = teclado.nextLine();
			id = criaturas.size();
			switch (tipo) {
			case "Elfo":
				nuevaCriatura = new Elfo(id, nombre);
				break;
			case "Orco":
				nuevaCriatura = new Orco(id, nombre);
				break;
			default:
				System.out.println("Opción equivocada");
				continue;
			}
			try {
				nuevaCriatura.addArtefacto(elegirArtefactos(artefactos));
			} catch (CriaturaSinNadaQueUsar e) {
				System.out.println("Primero hay que definir los Artefactos");
				break;
			}
			criaturas.add(nuevaCriatura);
			System.out.print("Mas (S/N)?");
			opcion = teclado.nextLine();

		} while (!opcion.toUpperCase().equals("N"));

	}

	private void crearArtefactos() {
		String tipo, nombre;
		String opcion = "S";
		Artefacto artefacto;
		int id;
		int numVidas;
		do {
			artefacto = null;
			System.out.print("Tipo artefacto (Arma/Veneno/Pocion): ");
			tipo = teclado.nextLine();
			System.out.print("Nombre: ");
			nombre = teclado.nextLine();
			id = artefactos.size();
			switch (tipo) {
			case "Arma":
				System.out.print("Cuantas vidas quita: ");
				numVidas = teclado.nextInt();
				teclado.nextLine();
				artefacto = new Arma(id, nombre, numVidas);
				break;
			case "Veneno":
				artefacto = new Veneno(id, nombre);
				break;
			case "Pocion":
				System.out.print("Cuantas vidas da: ");
				numVidas = teclado.nextInt();
				teclado.nextLine();
				artefacto = new Pocion(id, nombre, numVidas);
				break;
			default:
				System.out.println("Opción equivocada");
				continue;
			}
			if (artefacto != null)
				artefactos.add(artefacto);

			System.out.print("Mas (S/N)?");
			opcion = teclado.nextLine();

		} while (!opcion.toUpperCase().equals("N"));

	}

	public static void main(String[] args) {
		Juego ejercicio = new Juego();
		try {
			ejercicio.menu();
		} finally {
			ejercicio.guardarEnFichero();
		}

	}

}
