import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class TrazadorTablaAlumnos extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor, boolean isSelected, boolean hasFocus,
			int fila, int columna) {

		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna) {
		case 0:
			super.setHorizontalAlignment(CENTER);
			break;
		case 1:
			super.setHorizontalAlignment(LEFT);
			break;
		case 2:
			super.setHorizontalAlignment(LEFT);
			break;
		case 3:
			super.setHorizontalAlignment(LEFT);
			DecimalFormat numberFormat = new DecimalFormat("#0.00");
			super.setText(numberFormat.format((Double) valor));
			break;
		case 4:
			super.setHorizontalAlignment(CENTER);
			break;

		}

		return this;
	}

}
