package ejercicioDos;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MiCellRenderer extends DefaultListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

		if (String.valueOf(value).startsWith("(")) {
			c.setFont(c.getFont().deriveFont(Font.ITALIC));
			c.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		} else {
			c.setFont(c.getFont().deriveFont(Font.PLAIN));
			c.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		
		return c;
	}

}
