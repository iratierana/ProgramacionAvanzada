
import java.util.Scanner;

public class NumeroPrimo {

	Scanner teclado;
	
	public int esPrimo(int i){
		int suma = 0;
		for( int g = 2; g <= i/2 ; g++){
			if(i%g == 0){
				suma = suma + 1;
			}
		}
		return (suma);
	}
	
	public void miMain(){
		int i = 0 ;
		teclado = new Scanner(System.in);
		System.out.println("Introduce un entero");
		i = teclado.nextInt();
		
		if(esPrimo(i) == 0){
			System.out.println("El entero introducido es primo");
		}else{
			System.out.println("El entero introducido no es primo");
		}
		
	}
	
	public static void main(String[] args) {
		NumeroPrimo ejercicio = new NumeroPrimo();
		ejercicio.miMain();

	}

}
