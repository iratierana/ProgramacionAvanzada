package notasClase;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MisAcciones extends AbstractAction{
	
	String texto;
	
	public MisAcciones(String texto){
		super(texto);
		this.texto=texto;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand().toLowerCase()) {
		case "guardar":
			
			break;
		case "cargar":
			
			break;
		default:
			break;
		}
	}
}
