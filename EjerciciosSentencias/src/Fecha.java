
import java.util.Scanner;

public class Fecha {

	int dia = 0;
	int mes = 0;
	int a�o = 0;
	
	public void leer(){
		Scanner teclado;
		teclado  = new Scanner(System.in);
		
		System.out.println("Introduce fecha(numericamente)");
		System.out.println("Dia");
		dia = teclado.nextInt();
		System.out.println("Mes");
		mes = teclado.nextInt();
		System.out.println("A�o");
		a�o = teclado.nextInt();
		
	}
	
	public void comparar(){
		switch(mes){
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(dia != 31){
				System.out.println("El valor dia es incorrecto");
			}
			break;
		case 2:
			if(dia != 28){
				System.out.println("El valor dia es incorrecto");
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if (dia != 30){
				System.out.println("El valor dia es incorrecto");
			}
		}
		
		if(a�o < 0){
			System.out.println("El a�o introducido es incorrecto");
		}
		
	}
	
	public void miMain(){
		leer();
		comparar();
	}
	
	public static void main(String[] args) {
		Fecha ejercicio = new Fecha();
		ejercicio.miMain();

	}

}
