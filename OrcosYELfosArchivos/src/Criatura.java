

import java.util.ArrayList;

public abstract class Criatura implements Textualizable{
	
	final String [] ESTADOS = {"Sano","Envenenado"};
	int id;
	String nombre;
	int numVidas;
	int estado;
	
	ArrayList<Artefacto> artefactos;

	public Criatura (int id, String nombre){
		this.id = id;
		this.nombre = nombre;
		artefactos = new ArrayList<>();
	}
	
	public int getId(){
		return id;
	}
	public int getNumVidas() {
		return numVidas;
	}

	public void setNumVidas(int numVidas) {
		
		this.numVidas = numVidas;
		
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	public void addArtefacto(ArrayList<Artefacto> a){
		artefactos.addAll(a);
	}
	public ArrayList<Artefacto> getArtefactos(){
		return artefactos;
	}
	
	@Override
	public String toString() {
		String cadena;
		cadena = id + " "+nombre+" "+ "vidas: "+ numVidas+ " estado: "+ ESTADOS[estado]+"\n";
		cadena+= "\t\tartefactos: ";
		for (Artefacto a : artefactos){
			cadena+= a;
		}
		return cadena;
	}
	@Override
	public Object leerDeTexto(String linea, String separador){
		return null;
		
	}

	abstract public boolean actuar(Criatura c, Artefacto a); 
	@Override
	public Object leerDeTexto(String linea, String separador, ArrayList<Artefacto> lista) {
		Criatura ret;
		String fields[];
		
		fields = linea.split(separador);
		switch (fields[0]) {
		case "Orco":
			ret = new Orco(Integer.parseInt(fields[1]), fields[2]);
			break;
		case "Elfo":
			ret = new Elfo(Integer.parseInt(fields[1]), fields[2]);
			break;
		default:
			ret = null;
			break;
		}
		
		ret.setNumVidas(Integer.parseInt(fields[3]));
		ret.setEstado(Integer.parseInt(fields[4]));
		
		ArrayList<Artefacto> listaAux = new ArrayList<>();
		for(int i = 5; i < fields.length; i++){
			for(Artefacto a : lista){
				if(a.getId() == Integer.parseInt(fields[i])){
					listaAux.add(a);
				}
			}
		}
		ret.addArtefacto(listaAux);
		
		return ret;
	}
}
