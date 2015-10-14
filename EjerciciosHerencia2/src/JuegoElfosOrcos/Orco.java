package JuegoElfosOrcos;

import java.util.Random;

public class Orco extends Criatura{

	public Orco(int vida, String nombre, Artefacto artefacto) {
		super(vida, nombre, artefacto);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Orco [vida=" + vida + ", nombre=" + nombre + ", artefacto="
				+ artefacto.toString() + "]";
	}

	@Override
	public void accion(Criatura criaturaObjetivo) {
		Random random = new Random();
		if(random.nextInt(2) ==1){
			artefacto.accion(criaturaObjetivo);
			System.out.println(nombre+"ha acertado a "+ criaturaObjetivo.getNombre());
		}else{
			System.out.println(nombre+" ha fallado sobre"+ criaturaObjetivo.getNombre());
		}
	}


	
}
