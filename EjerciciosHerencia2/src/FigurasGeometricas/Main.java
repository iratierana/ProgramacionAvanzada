package FigurasGeometricas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	ArrayList<Figuras> figuras;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMenu();

	}

	private void miMenu() {
		figuras = new ArrayList<Figuras>();

		Scanner teclado = new Scanner(System.in);
		int opcion;

		do {
			System.out.println("****Que quieres hacer?****");
			System.out.println("1-Crear figura");
			System.out.println("2-Ver figuras ordenadas por area");
			System.out.println("3-Ver figuras ordenadas por posicion");
			System.out.println("0-Salir");

			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {
			case 1:
				crear();
				break;
			case 2:
				ordenarPorArea();
				break;
			case 3:
				ordenarPorPosicion();
				break;
			default:
				break;
			}

		} while (opcion != 0);

	}

	private void crear() {
		Scanner teclado = new Scanner(System.in);
		String figura;

		System.out.println("****Que figura quieres crear?****");
		System.out.println("cuadradado(c)/rectangulo(r)/triangulo(t)/circulo(o):");
		figura = teclado.nextLine();

		switch (figura.toLowerCase()) {
		case "c":
			int x,
			y,
			area,
			lado;

			System.out.println("Posicion del cuadrado? (x y)");
			x = teclado.nextInt();
			y = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Lado?");
			lado = teclado.nextInt();
			teclado.nextLine();
			area = lado * lado;

			Cuadrado cuadrado = new Cuadrado(area, x, y, lado);
			figuras.add(cuadrado);
			break;
		case "r":
			int lado1,
			lado2,
			areaRec,
			xRec,
			yRec;

			System.out.println("Posicion del rectangulo?(x,y)");
			xRec = teclado.nextInt();
			yRec = teclado.nextInt();
			teclado.nextLine();

			System.out.println("Lado1?");
			lado1 = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Lado2?");
			lado2 = teclado.nextInt();
			teclado.nextLine();

			areaRec = lado1 * lado2;

			Rectangulo rectangulo = new Rectangulo(areaRec, xRec, yRec, lado1,
					lado2);
			figuras.add(rectangulo);
			break;
		case "t":
			int base,
			altura,
			xTri,
			yTri,
			areaTri;

			System.out.println("Posicion del triangulo?(x y)");
			xTri = teclado.nextInt();
			yTri = teclado.nextInt();
			teclado.nextLine();

			System.out.println("Base?");
			base = teclado.nextInt();
			teclado.nextLine();
			System.out.println("Altura?");
			altura = teclado.nextInt();
			teclado.nextLine();

			areaTri = (base * altura) / 2;

			Triangulo triangulo = new Triangulo(areaTri, xTri, yTri, base,
					altura);
			figuras.add(triangulo);

			break;
		case "o":
			int radio,
			xO,
			yO,
			areaO;

			System.out.println("Posicion del circulo?(x y)");
			xO = teclado.nextInt();
			yO = teclado.nextInt();
			teclado.nextLine();

			System.out.println("Radio?");
			radio = teclado.nextInt();

			areaO = (int) (Math.PI * Math.pow(radio, 2));

			Circulo circulo = new Circulo(areaO, xO, yO, radio);
			figuras.add(circulo);

			break;

		default:
			break;
		}

	}

	private void ordenarPorArea() {
		MiComparador miComparador = new MiComparador();
		ListIterator<Figuras> figurasIt;

		Collections.sort(figuras, miComparador);

		figurasIt = figuras.listIterator();

		while (figurasIt.hasNext()) {
			System.out.println(figurasIt.next().toString());
		}
	}

	private void ordenarPorPosicion() {
		MiComparadorPosicion miComparadorPosicion = new MiComparadorPosicion();
		ListIterator<Figuras> figurasIt;

		Collections.sort(figuras, miComparadorPosicion);

		for (int i = 0; i < figuras.size(); i++) {
			System.out.println(figuras.get(i).toString());

		}
	}

}
