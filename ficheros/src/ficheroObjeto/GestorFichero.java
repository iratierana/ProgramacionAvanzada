package ficheroObjeto;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GestorFichero implements Serializable{

	public String nombres []={"Jon", "Ekaitz", "Jon", "Mikel", "Pablo", "Irati"};
	public String apellido1 [] = {"Apaolaza", "Irusta", "Gonzalez", "Retolaza", "Lombardo", "Eraña"};
	public String apellido2 [] = {"Artamendi", "Zubillaga", "Ramajo", "Coranti", "Papi", "Robles"};
	public String poblacion [] = {"Urretxu", "Aretxabaleta", "Gasteiz", "Bergara", "Aretxabaleta", "Eskoriatza"}; ;

	

	public void crearfichero(String nombreFichero){
		ObjectOutputStream out = null;
		Alumno alumno ;
		try {
			
			out = new ObjectOutputStream(new FileOutputStream(nombreFichero));
			for (int i = 0; i < 6; i++) {
				alumno = new Alumno(i+1, nombres[i], apellido1[i], apellido2[i], poblacion[i], i+2);
				out.writeObject(alumno);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
	}
	public void leer(String string) {
		ObjectInputStream in = null ;
		Alumno alumno;
		
		try {
			in = new ObjectInputStream(new FileInputStream("kakyta"));
			
			while ((alumno= (Alumno) in.readObject())!= null) {
				System.out.println(alumno);
				
			}
		} catch (EOFException e) {
			// TODO Auto-generated catch block
		
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
