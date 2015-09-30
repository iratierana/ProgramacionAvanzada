
import java.util.Scanner;

public class Bonoloto {

	int numerosBonoloto[];
	int numeroGanador[];
	int totalAciertos = 0;
	
	public void leer(){
		Scanner teclado;
		teclado = new Scanner(System.in);
		
		System.out.println("Introducir los seis numeros de la bonoloto");
		for(int i = 0; i < 6 ; i++){
				System.out.println((i + 1) + ".- ");
				numerosBonoloto[i] = teclado.nextInt();
			
		}
		
		System.out.println("Introducir los seis numeros ganadores");
		for(int i = 0; i < 6 ; i++){
				System.out.println((i + 1) + ".- ");
				numeroGanador[i] = teclado.nextInt();
		}
	}
	
	public void comparar(){
		for (int i = 0 ; i < 6 ; i++){
			for (int j = 0 ; j < 6 ; j++){
				if(numerosBonoloto[i] == numeroGanador[j]){
					System.out.println(numerosBonoloto[i]);
					totalAciertos++;
				}
			}
		}
	}
	
	public void miMain(){
		this.numerosBonoloto = new int[6];
		this.numeroGanador = new int[6];
		
		leer();
		comparar();
		System.out.println("Total aciertos-->"+ totalAciertos);
	}
	
	public static void main(String[] args) {
		Bonoloto ejercicio = new Bonoloto();
		ejercicio.miMain();
	}

}
