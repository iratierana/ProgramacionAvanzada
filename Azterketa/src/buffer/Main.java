package buffer;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	
	final static int NUMVECES = 25;
	ArrayList<Integer> numeros;

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();

	}

	private void miMain() {
		Random randon = new Random();
		numeros = new ArrayList<Integer>();
		
		int num;
		int put = 0;
		int get = 0;
		int contador = 0;
		int cont = 0;

		do{
		num = randon.nextInt(100);
		
		if (numeros.size() < 4) {
			contador = 0;
			if (randon.nextInt(2) == 1) {
				put = num;
				System.out.println("Guardado numero:" + put);
				numeros.add(put);
			} else {

				for (int i = 0; i < numeros.size(); i++) {
					contador++;

				}
				if (contador > 1) {
					numeros.remove(0);
				}else{
					System.out.println("No se ha podido sacar valor porque buffer esta vacio");
				}

			}
		}else{
			System.out.println("No se ha podido guardas" + num + "porque el buffer esta lleno");
		}

		BufferLimitado buffer = new BufferLimitado(4, put, get);
		cont++;
	
		
		}while(cont < 25);
	}
}
