import java.util.ArrayList;

public interface Textualizable {
	
	
	String guardarEnTexto(String separador);
	
	Object leerDeTexto(String linea, String separador);

	Object leerDeTexto(String linea, String separador, ArrayList<Artefacto> lista);
	
	

}
