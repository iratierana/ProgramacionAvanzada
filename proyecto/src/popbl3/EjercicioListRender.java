package popbl3;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class EjercicioListRender implements ListCellRenderer<Ejercicio> {

	

	@Override
	public Component getListCellRendererComponent(JList<? extends Ejercicio> list, Ejercicio value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JCheckBox check = new JCheckBox();
		check.setComponentOrientation(list.getComponentOrientation());
		check.setFont(list.getFont());
		check.setBackground(list.getBackground());
		check.setForeground(list.getForeground());
		check.setSelected(value.isSeleccionado());
		check.setText(value.getId()+ " " + value.getNombre());
        
        return check;
	}

}
