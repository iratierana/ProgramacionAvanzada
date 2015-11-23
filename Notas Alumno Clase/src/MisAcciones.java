import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MisAcciones extends AbstractAction {
	
	String texto;
	
	public MisAcciones(String texto) {
		super(texto);
		this.texto=texto;		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand().toLowerCase()) {
		case "guardar":
			break;
		case "cargar":	
			Buscador find=new Buscador();
			Principal.nombreFichero=find.nombre;
			Principal.listaAlumCarga.inicializarListaResultados(Principal.nombreFichero);
			break;
		}		
	}
}
