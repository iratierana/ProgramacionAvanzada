import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class AdaptadorNotas extends JLabel implements ListCellRenderer<ResultadoAprendizaje>{

	public Component getListCellRendererComponent(JList<? extends ResultadoAprendizaje> list,
	         ResultadoAprendizaje r,
	         int index,
	         boolean isSelected,
	         boolean cellHasFocus)
	     {
			
			
			 
			 if (r.getNota()>=5.0f){
				 setFont( new Font("Arial",Font.ITALIC,15));
				 setForeground(Color.black);
			 }else{
				 setFont( new Font("Arial",Font.ITALIC,15));
				 setForeground(Color.red);
			 }
			 this.setText(r.toString());
			 setOpaque(true);
	         return this;
	     }

}
