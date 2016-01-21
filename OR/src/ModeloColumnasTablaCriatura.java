import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaCriatura extends DefaultTableColumnModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TrazadorTabla trazador;
	
	public ModeloColumnasTablaCriatura(TrazadorTabla trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("ID", 0, 50));
		this.addColumn(crearColumna("Tipo", 1,100));
		this.addColumn(crearColumna("Nombre", 2, 100));
		this.addColumn(crearColumna("Num. armas", 3, 50));
		this.addColumn(crearColumna("num. pociones", 4, 50));
		this.addColumn(crearColumna("Envenenado", 5, 100));
	}

	private TableColumn crearColumna(String string, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice, ancho);
		columna.setHeaderValue(string);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		return columna;
	}

	
}
