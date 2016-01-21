package edu.mondragon.pa.listapeliculas;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JFrame;

public class MisAcciones extends AbstractAction {
	
	String texto;
	JFrame ventanaPri;

	public MisAcciones(String texto, Icon imagen, JFrame jf) {
		super(texto, imagen);
		this.texto=texto;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
