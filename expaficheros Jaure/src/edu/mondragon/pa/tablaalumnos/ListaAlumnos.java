package edu.mondragon.pa.tablaalumnos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ListaAlumnos extends ArrayList<Alumno> {
	
	final static String NOMBRE_FICHERO = "files/listaclase.dat";
	
	ListaAlumnos(){
		//this.leerTablaFichero();
	}
	
	public void leerTablaFichero() {
		
		ObjectInputStream  in=null;
		Alumno a;
		try {
			
			in=new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
			while((a=(Alumno) in.readObject())!=null){
				this.add(a);
			}				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(EOFException e ){
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void llenarPorDatos(String pobla, int edad, String nomb, String apell){
		ObjectInputStream  in=null;
		Alumno a;
		
		if(pobla!=null){
			try {				
				in=new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
				while((a=(Alumno) in.readObject())!=null){
					if(pobla.equals(a.getPoblacion())){
						this.add(a);
					}					
				}				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(EOFException e ){
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(edad!=-1){
			try {				
				in=new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
				while((a=(Alumno) in.readObject())!=null){
					if(edad==a.getEdad()){
						this.add(a);
					}					
				}				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(EOFException e ){
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(nomb!=null){
			try {				
				in=new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
				while((a=(Alumno) in.readObject())!=null){
					if(nomb.equals(a.getNombre())){
						this.add(a);
					}					
				}				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(EOFException e ){
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(apell!=null){
			try {				
				in=new ObjectInputStream(new FileInputStream(NOMBRE_FICHERO));
				while((a=(Alumno) in.readObject())!=null){
					if(apell.equals(a.getApellido1())){
						this.add(a);
					}					
				}				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(EOFException e ){
				
			}catch (IOException e) {
				e.printStackTrace();
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}finally {
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
