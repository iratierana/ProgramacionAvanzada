

import java.util.Scanner;

public class PorDebajoDeLaMedia {

	int numeros[];
	

	public void leer(){
		Scanner teclado;
		teclado = new Scanner(System.in);
		
		System.out.println("Introduce diez enteros");
		for (int i = 0; i < 10; i++){
			numeros [i] = teclado.nextInt();
		}
	}
	
	public int media(){
		int valor = 0;
		
		for (int i = 0; i < 10; i++){
			valor = valor + numeros[i];
		}
		valor = valor / 10;
		
		return (valor);
	}
	public void escribir(int val){
		for(int i = 0; i < 10; i++){
			if(numeros[i]<val){
			System.out.println(numeros[i]);
			}
		}
	}

	public void miMain(){
		this.numeros = new int[10]; 
		int val = 0;
		leer();
		val = media();
		escribir(val);
	}
	
	public static void main(String[] args) {
		PorDebajoDeLaMedia ejercicio = new PorDebajoDeLaMedia();
		ejercicio.miMain();
	}

}
