package popbl3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Ejercicio {
	
	final String SEPARADOR = "$";
	
	String id;
	String nombre;
	String descripcion;
	String directorioGIF;
	String resultado;
	

	String fecha;
	int repeticiones;
	int repeticionesRealizadas;
	
	boolean seleccionado;
	
	
	public Ejercicio(String id, String nombre, String descripcion, String directorioGIF){
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.directorioGIF = directorioGIF;
		this.resultado = "";
		this.fecha = fechaDelSistema();
		this.repeticiones = 5; 
		this.seleccionado = false;
	}
	
	public Ejercicio(String id, String nombre, String descripcion, String directorioGIF, String resultado,
					 String fecha, int repeticiones, boolean seleccionado){
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.directorioGIF = directorioGIF;
		this.resultado = resultado;
		this.fecha = fecha;
		this.repeticiones = repeticiones; 
		this.seleccionado = seleccionado;
	}

	private String fechaDelSistema() {
		String fecha;
		Calendar calendario = new GregorianCalendar();
        
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);
       
        fecha = dia+"/"+(mes+1)+"/"+año;
        
		return fecha;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getDirectorioGIF() {
		return directorioGIF;
	}
	public void changeSelection() {
		this.seleccionado = !this.seleccionado;
	}
	
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return id;
		case 1: return nombre;
		case 2: return fecha;
		case 3: return new Integer(repeticiones);
		case 4: return resultado;
		default: return null;
		}
	}
	
	public String guardar(){
		String linea;
		
		linea  = id+SEPARADOR+nombre+SEPARADOR+descripcion+SEPARADOR+
				 directorioGIF+SEPARADOR+resultado+SEPARADOR+fecha+SEPARADOR+
				 repeticiones+SEPARADOR+seleccionado;
		
		return linea;
	}
}
