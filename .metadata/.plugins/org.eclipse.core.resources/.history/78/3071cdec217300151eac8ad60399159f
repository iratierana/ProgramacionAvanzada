package cuatroEnRaya;

public class Tablero {

	int matriz[][];
	
	final static int ROJO = 1;
	final static int AZUL = 2;
	
	int filas ;
	int columnas;
	
	
	public Tablero( int filas, int columnas) {
		super();
		this.filas = filas;
		this.columnas = columnas;
	}
	
	public void inicializar(){
		matriz =new int[filas][columnas];
		
		for(int i = 0; i < filas ; i++){
			for(int j = 0 ; j < columnas ; j++){
				matriz[i][j] = 0;
			}
		}
	}
	public void darValor(int x, int y, int jugador){
		matriz[x][y] = jugador;
	}

	public Integer recibirValor(int i, int j) {
		return matriz[i][j];
	}
	
}
