import java.io.File;

import javax.swing.JFileChooser;

public class Buscador {
	
	String nombre;
	
	public Buscador(){
		super();
		this.desplegarBuscador();
	}

	public void desplegarBuscador(){
		File fich;
		JFileChooser buscador=new JFileChooser();
		if(buscador.showOpenDialog(buscador)==JFileChooser.APPROVE_OPTION){
			fich=buscador.getSelectedFile();
			//nombre=fich.getName();
			nombre=fich.getPath();
		}		
	}
}
