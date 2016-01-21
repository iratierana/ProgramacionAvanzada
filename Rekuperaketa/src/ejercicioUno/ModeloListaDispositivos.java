package ejercicioUno;

import javax.swing.DefaultListModel;

public class ModeloListaDispositivos extends DefaultListModel<Dispositivo>{

	ListaDispositivos lista;
	
	public ModeloListaDispositivos(ListaDispositivos d) {
		this.lista = d;
		this.cargarDispositivos(lista);
	}

	private void cargarDispositivos(ListaDispositivos d) {
		for(Dispositivo disp : d){
			this.addElement(disp);
		}
		
	}
}
