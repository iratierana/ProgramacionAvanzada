import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	Scanner teclado;
	ArrayList<Forma> lista;

	public Principal() {
		teclado = new Scanner(System.in);
		leerDeFichero("formas.txt");
		for(Forma f : lista){
			System.out.println(f);
		}
		jugar();
	}

	private void jugar() {
		int x, y;
		String s[];
		while(true){
			try{
				System.out.println("Introduce las coordenadas del punto: ");
				s = teclado.nextLine().split("[ ]");
				x = Integer.parseInt(s[0]);
				y = Integer.parseInt(s[1]);
				System.out.println("Has inpactado en:");
				ArrayList<Forma> lAux = comprobarImpacto(x, y);
				for(Forma f: lAux){
					System.out.println(f.getNombre());
				}
			}catch(InputMismatchException e){
			}catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				System.out.println("Formato no valido");
			}
			
		}
	}

	private void leerDeFichero(String file) {
		BufferedReader in = null;
		lista = new ArrayList<>();
		Forma aux;
		String linea;
		String nombre;
		int x, y, p1, p2;
		String[] fields;

		try {
			in = new BufferedReader(new FileReader(file));

			while ((linea = in.readLine()) != null) {
				try {
				if (linea == "") {
					} else {
						switch (linea.toLowerCase()) {
						case "rectangulo":
							linea = in.readLine();
							nombre = linea;
							linea = in.readLine();
							fields = linea.split("[ ]");
							x = Integer.parseInt(fields[0]);
							y = Integer.parseInt(fields[1]);
							linea = in.readLine();
							fields = linea.split("[ ]");
							p1 = Integer.parseInt(fields[0]);
							p2 = Integer.parseInt(fields[1]);
							aux = new Rectangulo(nombre, x, y, p1, p2);
							break;
						case "circulo":
							linea = in.readLine();
							nombre = linea;
							linea = in.readLine();
							fields = linea.split("[ ]");
							x = Integer.parseInt(fields[0]);
							y = Integer.parseInt(fields[1]);
							linea = in.readLine();
							p1 = Integer.parseInt(linea);
							aux = new Circulo(nombre, x, y, p1);
							break;
						default:
							throw new FormaDesconocidaException();
						}
						lista.add(aux);
					}
				} catch (FormaDesconocidaException e) {
					System.out.println("Forma deconozida " + linea);
					for(int i = 0; i < 3;i++)linea = in.readLine();
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encontro el archivo");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Error en la lectura del archivo");
			System.exit(0);
		} catch (NullPointerException e) {
			System.out.println("Error en la lectura del archivo");
			System.exit(0);
		} finally {
			try{in.close();}catch(Exception e){};
		}

	}

	public ArrayList<Forma> comprobarImpacto(int x, int y) {
		ArrayList<Forma> ret = new ArrayList<>();
		for (Forma f : lista) {
			if (f.impacto(x, y)) {
				ret.add(f);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Principal p = new Principal();
		
	}

}
