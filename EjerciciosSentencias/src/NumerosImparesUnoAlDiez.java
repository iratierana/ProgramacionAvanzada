
public class NumerosImparesUnoAlDiez {

	public boolean esImpar(int i){
		return(i%2 != 0);
	}
	
	public void miMain(){
		for (int i = 0 ; i <= 10 ; i++){
			if(esImpar(i)){
				System.out.println("El valor " + i +" es impar");
			}
		}
	}
	
	public static void main(String[] args) {
	NumerosImparesUnoAlDiez ejercicio = new NumerosImparesUnoAlDiez();
	ejercicio.miMain();
	
	}

}
