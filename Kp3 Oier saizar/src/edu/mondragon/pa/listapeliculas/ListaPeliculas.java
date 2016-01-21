package edu.mondragon.pa.listapeliculas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;

public class ListaPeliculas extends DefaultListModel<Pelicula> implements Observable {
	private static final long serialVersionUID = 1L;
	
	List<Observer> observadores;
	List<Pelicula> peliculas;
	int cursor = 0;
	final String ARCHIVO = "files/peliculas.txt";
	
	public ListaPeliculas(){
		observadores = new ArrayList<Observer>();
		peliculas = new ArrayList<>();
		cargarPeliculasMAS();
	}
	
	void cargarPeliculasMAS(){
		String s;
		BufferedReader in = null;
		int kont = 0;
		
		try {
			in = new BufferedReader(new FileReader(ARCHIVO));
			while((s = in.readLine()) != null || kont == cursor +5){
				if(kont > cursor){
					peliculas.add(añadirPelicula(s));
				}
				kont++;
			}
			in.close();
			
		} catch (IOException e) {
			System.out.println("Ez dago fitxerorik");
		} finally{
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		cursor = kont;
	}
	
	void cargarPeliculasMENOS(){

		String s;
		BufferedReader in = null;
		int kont = peliculas.size()-1;
		
		try {
			in = new BufferedReader(new FileReader(ARCHIVO));
			while((s = in.readLine()) != null || kont == cursor -5){
				if(kont < cursor){
					peliculas.add(añadirPelicula(s));
				}
				kont--;
			}
			in.close();
			
		} catch (IOException e) {
			System.out.println("Ez dago fitxerorik");
		} finally{
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		cursor = kont;
	}
	
	private Pelicula añadirPelicula(String s) {
		String param[];
		
		param = s.split("[$]");
		
		return new Pelicula(param[0],param[1],Integer.parseInt(param[2]),param[3], param[4], param[5]);
	}

	@Override
	public void addObserver(Observer o) {
		observadores.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		observadores.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : observadores){
			o.update(this);
		}
	}
	
	public void addElement(Pelicula a){
		if(super.size() < 5){
		super.add(0, a);
		this.notifyObservers();
		}
	}
}
