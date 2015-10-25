package ejercicioDos;

import java.util.ArrayList;

public class Generador {
	ArrayList<Integer> numeros;
	int valor;

	public Generador(int valor) {
		super();
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Generador [valor=" + valor + "]";
	}

	public void numeroCapicua() {
		long falta, numeroInvertido, resto;
		//while (valor <= 0)
			//;
		

		falta = valor;
		numeroInvertido = 0;
		resto = 0;

		while (falta != 0) {
			resto = falta % 10;
			numeroInvertido = numeroInvertido * 10 + resto;
			falta = falta / 10;
		}
		if (numeroInvertido == valor) {
			toString();
		}
	}

	public void numeroPrimo() {

		int cont = 0;

		for (int p = 1; p <= this.valor; p++) {

			if (this.valor % p == 0)
				cont++;
		}
		if (cont <= 2)
			toString();

	}

	public void numeroSuma() {

        int resultado = 0;
        
	while (valor > 0)
        {
            resultado += valor % 10;
            valor = valor / 10;
        }
        
     if(resultado == 13){
    	 toString();
     }
	}
}