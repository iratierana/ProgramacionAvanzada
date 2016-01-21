import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class ModeloColumnasTablaAlumnos extends DefaultTableColumnModel {
	
	TrazadorTablaAlumnos trazador;
	
	public ModeloColumnasTablaAlumnos(TrazadorTablaAlumnos trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Nº",0,5));
		this.addColumn(crearColumna("Nombre",1,100));
		this.addColumn(crearColumna("Apellido",2,100));
		this.addColumn(crearColumna("Nota",3,5));
		this.addColumn(crearColumna("Ciudad",4,100));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);
		
		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}
	
	
	
}
