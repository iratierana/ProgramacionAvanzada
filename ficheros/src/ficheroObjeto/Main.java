package ficheroObjeto;

public class Main {

	public static void main(String[] args) {
		Main ejercicio = new Main();
		ejercicio.miMain();
	}

	private void miMain() {
		GestorFichero gestor = new GestorFichero();
		
		gestor.crearfichero("kakyta");
		gestor.leer("kakyta");
	}
}
