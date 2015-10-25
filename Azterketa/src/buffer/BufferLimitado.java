package buffer;

import java.util.ArrayList;

public class BufferLimitado {
	ArrayList<Integer> numeros;
	
	int capacidad;
	int put;
	int get;
	
	public BufferLimitado( int capacidad, int put, int get) {
		super();
		this.capacidad = capacidad;
		this.put = put;
		this.get = get;
	}
	
	
	
}
