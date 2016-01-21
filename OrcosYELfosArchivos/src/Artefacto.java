import java.util.ArrayList;

public abstract class Artefacto implements Textualizable{
	int id;
	String nombre;
	
	public Artefacto(int id, String nombre){
		this.id = id;
		this.nombre = nombre;
	}
	abstract void activar (Criatura c);
	@Override
	public String toString() {
		return "Artefacto [id=" + id + ", nombre=" + nombre + "]";
	}
	
	@Override
	public Object leerDeTexto(String linea, String separador, ArrayList<Artefacto> lista) {
		return null;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Object leerDeTexto(String linea, String separador){
		Artefacto ret;
		String fields[];
		
		fields = linea.split(separador);
		switch (fields[0]) {
		case "Arma":
			ret = new Arma(Integer.parseInt(fields[1]), fields[2],Integer.parseInt(fields[3]));
			break;
		case "Pocion":
			ret = new Pocion(Integer.parseInt(fields[1]), fields[2],Integer.parseInt(fields[3]));
			break;
		case "Veneno":
			ret = new Veneno(Integer.parseInt(fields[1]), fields[2]);
			break;

		default:
			ret = null;
			break;
		}
		
		return ret;
		
		
		
	}
	

}
