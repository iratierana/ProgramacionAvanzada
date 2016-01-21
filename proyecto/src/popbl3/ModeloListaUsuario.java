package popbl3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class ModeloListaUsuario extends DefaultListModel<Usuario>{

	String fichUsuarios;
	final String SEPARADOR = "$";
	String tipoUsuario;
	
	public ModeloListaUsuario (String tipoUsuario){
		super();
		this.tipoUsuario = tipoUsuario;
		inicializar(null);
	}
	
	public ModeloListaUsuario (String tipoUsuario, Fisio fisio){
		super();
		this.tipoUsuario = tipoUsuario;
		inicializar(fisio);
	}

	public void inicializar(Fisio fisio) {
		BufferedReader in = null;
		String linea = null;
		String valores[];
		
		////////////////////////////////////////////////////////////////////////////////////////
		switch(tipoUsuario.toLowerCase()){
		case "fisio":
			fichUsuarios = "files/fisioterapeutas.txt";
			break;
		case "admin":
			fichUsuarios = "files/administradores.txt";
			break;
		case "paciente":
			fichUsuarios = "files/pacientes.txt";
			break;
		default:
			System.out.println("Fichero no valido");
			break;
		}
		
		
		try {
			in = new BufferedReader (new FileReader(fichUsuarios));
			while ((linea = in.readLine())!=null){
				valores = linea.split("["+SEPARADOR+"]");
				
					switch (tipoUsuario.toLowerCase()) {
					case "fisio":
						Fisio f = new Fisio(valores[0],valores[1],
								valores[2],valores[3],valores[4]);
						
						this.addElement(f);
						break;
					case "admin":
						Administrador a = new Administrador(valores[0],valores[1],
								valores[2],valores[3],valores[4]);
						this.addElement(a);
						break;
					case "paciente":
						if(fisio != null){
							if(valores[6].equals(fisio.getUserName())){
								cargarPaciente();
								Paciente p = new Paciente(valores[0],valores[1],valores[2],
										valores[3],valores[4],valores[5],valores[6]);
								ArrayList<Ejercicio> ejercicios = new ArrayList<>();
								int numEjercicios = Integer.valueOf(valores[7]);
								
								for (int i = 0; i < numEjercicios ; i++){
									linea = in.readLine();
									valores = linea.split("["+SEPARADOR+"]");
									ejercicios.add(new Ejercicio(valores[0],valores[1],valores[2],valores[3],
																 valores[4],valores[5],Integer.valueOf(valores[6]),
																 Boolean.valueOf(valores[7])));
								}
								p.setEjercicios(ejercicios);
								
								this.addElement(p);
							}else{
								int numEjercicios = Integer.valueOf(valores[7]);
								
								for (int i = 0; i < numEjercicios ; i++){
									linea = in.readLine();
								}
							}
						}else{
							Paciente p = new Paciente(valores[0],valores[1],valores[2],
									valores[3],valores[4],valores[5],valores[6]);
							ArrayList<Ejercicio> ejercicios = new ArrayList<>();
							int numEjercicios = Integer.valueOf(valores[7]);
							
							for (int i = 0; i < numEjercicios ; i++){
								linea = in.readLine();
								valores = linea.split("["+SEPARADOR+"]");
								ejercicios.add(new Ejercicio(valores[0],valores[1],valores[2],valores[3],
															 valores[4],valores[5],Integer.valueOf(valores[6]),
															 Boolean.valueOf(valores[7])));
							}
							p.setEjercicios(ejercicios);
							
							this.addElement(p);
						}
						
						break;
					default:
						break;
					}
				
			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close();} catch (IOException e) {}
			}
		}
		////////////////////////////////////////////////////////////////////////////////////////
	}
	
	private void cargarPaciente() {
		// TODO Auto-generated method stub
		
	}

	public void guardar(){
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(fichUsuarios));
			for(int i = 0; i < this.getSize(); i++){
				out.println(this.getElementAt(i).guardar());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (out!=null){
				out.close();
			}
		}
	}
}
