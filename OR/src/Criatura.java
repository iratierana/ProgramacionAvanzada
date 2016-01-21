

import java.util.ArrayList;

public abstract class Criatura {
	
	final String [] ESTADOS = {"Sano","Envenenado"};
	int id;
	String nombre;
	int numVidas;
	int estado;
	
	ArrayList<Artefacto> artefactos;

	public String getNombre() {
		return nombre;
	}

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

	public Object getFieldAt(int columna){
		int countA = 0, countP = 0;
		switch(columna){
		case 0: return id;
		case 1:  if(this instanceof Orco){
			return "Orco";
		}else{
			return "Elfo";
		}
		case 2:return nombre;
		case 3: for(int i = 0; i < artefactos.size(); i++){
			if(artefactos.get(i) instanceof Arma){
				countA++;
			}
		}return countA;
		case 4: for(int i = 0; i < artefactos.size(); i++){
			if(artefactos.get(i) instanceof Pocion){
				countP++;
			}
		}return countP;
		case 5: return new Integer(estado);
		default: return null;
		}
		
	}
	abstract public boolean actuar(Criatura c, Artefacto a);

	public String escribir() {
		String linea = id+"$"+nombre+"$"+numVidas+"$"+estado+"$";
		for(Artefacto a : artefactos){
			linea+=a.getId()+"&";
		}
		return linea;
	} 
}
