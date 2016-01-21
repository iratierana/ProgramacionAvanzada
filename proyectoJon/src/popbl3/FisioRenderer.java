package popbl3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

public class FisioRenderer extends JLabel implements ListCellRenderer<Fisio> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Fisio> list,
	         Fisio e,
	         int index,
	         boolean isSelected,
	         boolean cellHasFocus)
	     {
		Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
		setFont(new Font("Arial", Font.BOLD, 16));

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		if (isSelected && cellHasFocus) {
			setBorder(border);
		} else {
			setBorder(null);
		}

		this.setText(e.toString());
		this.setOpaque(true);
		return this;
	}

}
