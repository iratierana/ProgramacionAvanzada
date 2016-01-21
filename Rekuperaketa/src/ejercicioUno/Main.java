package ejercicioUno;

import java.util.Scanner;

import javax.swing.JList;

public class Main {
	
	JList<Dispositivo> listaDeDispositivo;
	ListaDispositivos listaDispo;
	ModeloListaDispositivos modeloLista;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.primero();
		ejercicio.lista();
		
	}
	private void primero(){
		int opci = 0;

		do {
			opci = menu();

			switch (opci) {
			case 1:
				verEstado();
				break;

			case 2:
				cambiarEstado();
				break;

			default:
				System.out.println("La opcion no es valida");
				break;
			}
		} while (opci != 0);
	}

	private void lista(){
		listaDeDispositivo = new JList<Dispositivo>();
		listaDispo = new ListaDispositivos();
		modeloLista = new ModeloListaDispositivos(listaDispo);
		
		listaDeDispositivo.setModel(modeloLista);
	}
	
	private int menu() {
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;

		System.out.println("-----------------");
		System.out.println("1.- Ver estado");
		System.out.println("2.- Cambiar estado");
		System.out.println("0.- Salir");

		opcion = teclado.nextInt();
		teclado.nextLine();

		return opcion;

	}
	private void verEstado(){
		for(int i = 0 ; i < listaDispositivo.size(); i++){
			System.out.println(listaDispositivo.get(i).toString());
		}
		
		
	}
	
	private void cambiarEstado() {
		for(int i = 0 ; i < listaDispositivo.size(); i++){
			System.out.println(listaDispositivo.get(i).toString());
		}
		
		System.out.println("");
	}

}
