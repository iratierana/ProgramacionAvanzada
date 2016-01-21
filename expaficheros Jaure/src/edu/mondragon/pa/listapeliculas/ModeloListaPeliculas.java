package edu.mondragon.pa.listapeliculas;
import javax.swing.DefaultListModel;

public class ModeloListaPeliculas extends DefaultListModel<Pelicula>{
	
	ListaPeliculas lista;

	ModeloListaPeliculas(ListaPeliculas p){
		this.lista=p;
		this.cargarPrimerasPeliculas(lista);
	}

	private void cargarPrimerasPeliculas(ListaPeliculas p) {
		int kont=0;			
		for(Pelicula peli: p){
			this.addElement(peli);
			kont++;
			if(kont>=5){
				break;
			}
		}
	}
	
	public void cargarSigCincoPelis(ListaPeliculas p){
		int kont=0;		
		int konta=0;
		for(Pelicula peli: p){			
			kont++;
			if(kont>=(Principal.kont+1)){
				this.addElement(peli);
				konta++;
				if(konta>=5){
					break;
				}
			}
		}
		
	}
	
	public void cargarAntCincoPelis(ListaPeliculas p){
		
		int kont=0;		
		int konta=0;
		for(Pelicula peli: p){			
			kont++;
			if((kont+5)>=(Principal.kont+1)){
				this.addElement(peli);
				konta++;
				if(konta>=5){
					break;
				}
			}
		}
	}

}
