package edu.mondragon.pa.listapeliculas;

public class Pelicula {

	String caratula;
	String titulo;
	int a�o;
	String director;
	String nacionalidad;
	String genero;
	
	
	public Pelicula (String c, String t,int a�o, String d, String n, String g){
		this.caratula = c;
		this.titulo = t;
		this.a�o = a�o;
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


	public int getA�o() {
		return a�o;
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


	@Override
	public String toString() {
		return "Pelicula [caratula=" + caratula + ", titulo=" + titulo + ", a�o=" + a�o + ", director=" + director
				+ ", nacionalidad=" + nacionalidad + ", genero=" + genero + "]";
	}
	
	
}
