
public class Conversor {

	public static double convertir(Object tabla[][], double cantidad, int origen, int destino){
		double aux;
		aux = cantidad / (double)tabla[origen][Ventana.CONVERSION];
		aux = aux * (double)tabla[destino][Ventana.CONVERSION];
		return aux;
		
	}
	
}
