import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTabla extends DefaultTableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor, boolean isSelected, boolean hasFocus, int fila,
			int columna) {
		// TODO Auto-generated method stub
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch(columna){
			case 0: 
			case 1:
			case 2: super.setHorizontalAlignment(LEFT);break;
			case 3: 
			case 4:
			case 5: super.setHorizontalAlignment(CENTER);break;
			default: break;
		}
		return this;
	}
	
	

}
