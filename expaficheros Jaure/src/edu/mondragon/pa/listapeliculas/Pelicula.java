package edu.mondragon.pa.listapeliculas;

public class Pelicula {

	String caratula;
	String titulo;
	int año;
	String director;
	String nacionalidad;
	String genero;
	
	
	public Pelicula (String c, String t,int año, String d, String n, String g){
		this.caratula = c;
		this.titulo = t;
		this.año = año;
		this.director = d;
		this.nacionalidad = n;
		this.genero = g;
	}


	public String getCaratula() {
		return caratula;
	}


	public String getTitulo() {
		return titulo;
	}


	public int getAño() {
		return año;
	}


	public String getDirector() {
		return director;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public String getGenero() {
		return genero;
	}
	
}
