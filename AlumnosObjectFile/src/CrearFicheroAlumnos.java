import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

public class CrearFicheroAlumnos {
	
	public static void crearFichero(){
		Random rng = new Random();
		int num = 50;
		String[] nombres = {"Joanes", "Alopecio", "Cailou", "Cayllu", "Juan", "Pedro", "Nestor", "Pepe", "Felipe", "John"};
		String[] apellidos = {"Plazaola", "Stevens", "Marquez", "Burlao", "Nieve", "Picapiedra", "Arana", "Palote", "Moreno", "Cena"};
		String[] ciudades = {"Calcuta", "Vitoria", "Roma", "Pisa", "JaponCity", "Gotham", "Bilbau", "Pekin", "Tokyo", "Arrasate"};
		ObjectOutputStream out = null;
		Alumno a;
		try {
			out = new ObjectOutputStream(new FileOutputStream("alumnos.alu"));
			for(int i = 0; i < num; i++){
				a = new Alumno(nombres[rng.nextInt(10)], apellidos[rng.nextInt(10)], (rng.nextDouble()*10), ciudades[rng.nextInt(10)]);
				out.writeObject(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{out.close();}catch(Exception e ){};
		}
		
		ObjectInputStream in = null;
				
		try {
			in = new ObjectInputStream(new FileInputStream("alumnos.alu"));
			while(true){
				System.out.println((Alumno) in.readObject());
			}
		}catch (EOFException e) {
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try{in.close();}catch(Exception e ){};
		}
		
		System.out.println("Los que son de Gotham:");
		
		try {
			in = new ObjectInputStream(new FileInputStream("alumnos.alu"));
			while(true){
				if((a = (Alumno) in.readObject()).getCiudad().equals("Gotham"))System.out.println(a);
			}
		}catch (EOFException e) {
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try{in.close();}catch(Exception e ){};
		}
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		crearFichero();
	}
	
	
	
	

}
