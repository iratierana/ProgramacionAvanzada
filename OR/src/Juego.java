

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Juego implements Serializable,ListSelectionListener{
	ArrayList<Criatura> criaturas;
	ArrayList<Artefacto> artefactos;
	final static String nombreFichero = "Artefactos.txt";
	final static String nombreFicheroCriatura = "Criaturas.txt";
	Scanner teclado;
	JFrame pantalla;
	ModeloTablaCriatura tabla;
	TrazadorTabla trazador;
	ModeloColumnasTablaCriatura columna;
	JTable vTabla;
	JScrollPane panelS;
	
	public Juego(){
		criaturas = new ArrayList<>();
		artefactos = new ArrayList<>();
		teclado = new Scanner ( System.in);
		
	}
	public void menu(){
		int opcion= 0;
		do{
			try{
			
			
				System.out.println("1.- Crear Artefactos");
				System.out.println("2.- Crear Criaturas");
				System.out.println("3.- Ver Criaturas");
				System.out.println("4.- Jugar");
				System.out.println("5.- Ver Criaturas en tabla");
				System.out.println("0.- Salir");
				System.out.print("Selecciona opcion: ");
				opcion = teclado.nextInt(); teclado.nextLine();
				switch (opcion){
				case 1: crearArtefactos();break;
				case 2: crearCriaturas(); break;
				case 3: verCriaturas(); break;
				case 4: jugar();break;
				case 5: this.crearPantalla();
				
				case 0: escribirCriaturasFichero(); escribirArtefactosEnFichero(); break;
				default: System.out.println("opción no válida");
				}
				
			}catch (InputMismatchException e){
				System.out.println("Elija una opción valida");
			}
		}while (opcion != 0);
	}
	
	public void crearPantalla(){
		pantalla = new JFrame("Elfos y Orcos");
		pantalla.setSize(800, 600);
		pantalla.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pantalla.setContentPane(crearPanelVentana());
		pantalla.setVisible(true);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(20,20));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panelS = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(panelS,BorderLayout.CENTER);
		
		crearTabla();
		return panel;
		
	}
	private Container crearTabla() {
		trazador = new TrazadorTabla();
		columna = new ModeloColumnasTablaCriatura(trazador);
		tabla = new ModeloTablaCriatura(columna);
		tabla.setListaCriaturas(criaturas);
		vTabla = new JTable(tabla,columna);
		vTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vTabla.getSelectionModel().addListSelectionListener(this);
		vTabla.setFillsViewportHeight(true);
		vTabla.setRowSelectionInterval(0, 0);
		panelS.setViewportView(vTabla);
		
		return null;
	}
	private void verCriaturas() {
		ListIterator<Criatura> it = criaturas.listIterator();
		while (it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	private void jugar() {
		int idm,idc;
		Criatura mueve,contra;
		Artefacto a;
		String opcion="S";
		do{
			try{
				System.out.print("Id criatura que mueve: ");
				idm = teclado.nextInt();teclado.nextLine();
				mueve = findCriatura(idm);
				a = elegirArtefacto(mueve.getArtefactos());
				System.out.print("Id criatura sobre la que actua");
				idc = teclado.nextInt(); teclado.nextLine();
				contra = findCriatura(idc);
				mueve.actuar(contra, a);
				System.out.print("Mas (S/N)?");
				opcion = teclado.nextLine();
			}catch (InputMismatchException e){
				System.out.println("El formato introducido no es correcto");
			}catch (CriaturaNoExisteException e){
				System.out.println(e.getMessage());
			}catch (CriaturaSinNadaQueUsar e){
				System.out.println(e.getMessage());
			}
		}while (!opcion.equals("N"));
	}
	
	private Artefacto elegirArtefacto(ArrayList<Artefacto> artefactos) throws CriaturaSinNadaQueUsar{
		Artefacto artefacto = null;
		int opcion;
		if (artefactos.size()==0) throw new CriaturaSinNadaQueUsar( "No hay artefactos");
		if (artefactos.size()==1) return artefactos.get(0);
		
		do{
			
			ListIterator<Artefacto > it = artefactos.listIterator();
			while (it.hasNext()){
				System.out.println(it.next());
			}
			try{
				System.out.print("Usa? : ");
				opcion = teclado.nextInt(); teclado.nextLine();
				artefacto = artefactos.get(opcion);
				
			}catch (InputMismatchException e){
				System.out.println("Elija el id adecuado");
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
			
		}while  (artefacto == null);
		return artefacto;
	}
	private ArrayList<Artefacto> elegirArtefactos(ArrayList<Artefacto> artefactos) throws CriaturaSinNadaQueUsar {
		ArrayList<Artefacto> lista = new ArrayList<>();
		
		Artefacto artefacto=null;
		String opcionSalida;
		
		if (artefactos.size()==0) throw new CriaturaSinNadaQueUsar( "No hay artefactos");
		if (artefactos.size()==1) {
			lista.add(artefactos.get(0));
			return lista;
		}
		
		do{
			
			
			artefacto =elegirArtefacto (artefactos);
			
			lista.add(artefacto);
			System.out.print("Quieres añadir más artefactos (s/n)?: ");
			opcionSalida = teclado.nextLine();
			
		}while (opcionSalida.toLowerCase().equals("s"));
		return lista;
	}
	private Criatura findCriatura(int idc) throws CriaturaNoExisteException {
		
		Criatura criatura = null;
		ListIterator<Criatura> it = criaturas.listIterator();
		while (it.hasNext()){
			if ((criatura =it.next()).getId()==idc){
				return criatura;				
			}
		}
		throw new CriaturaNoExisteException ("No existe una criatura con ese identificador");
		
	}
	private void crearCriaturas() {
		String tipo,nombre;
		String opcion="S";
		Criatura nuevaCriatura;
		int id;
		do{
			
			System.out.print("Tipo criatura (Elfo/Orco): ");
			tipo = teclado.nextLine();
			System.out.print("Nombre: ");
			nombre = teclado.nextLine();
			id = criaturas.size();
			switch (tipo){
			case "Elfo": nuevaCriatura = new Elfo(id,nombre);break;
			case "Orco": nuevaCriatura = new Orco(id,nombre);break;
			default: System.out.println("Opción equivocada");continue;
			}
			try {
				nuevaCriatura.addArtefacto(elegirArtefactos(artefactos));
			} catch (CriaturaSinNadaQueUsar e) {
				System.out.println ("Primero hay que definir los Artefactos");
				break;
			}
			criaturas.add(nuevaCriatura);
			//tabla.setListaCriaturas(criaturas);
			System.out.print("Mas (S/N)?");
			opcion = teclado.nextLine();
			
		}while (!opcion.toUpperCase().equals("N"));
		
	}
	
	private void crearArtefactos() {
		String tipo,nombre;
		String opcion="S";
		Artefacto artefacto;
		int id;
		int numVidas;
		do{
			artefacto = null;
			System.out.print("Tipo artefacto (Arma/Veneno/Pocion): ");
			tipo = teclado.nextLine();
			System.out.print("Nombre: ");
			nombre = teclado.nextLine();
			id = artefactos.size();
			switch (tipo){
			case "Arma":
				System.out.print("Cuantas vidas quita: ");
				numVidas = teclado.nextInt(); teclado.nextLine();
				artefacto = new Arma(id,nombre,numVidas);break;
			case "Veneno": artefacto = new Veneno(id,nombre);break;
			case "Pocion":
				System.out.print("Cuantas vidas da: ");
				numVidas = teclado.nextInt(); teclado.nextLine();
				artefacto = new Pocion(id,nombre,numVidas);break;
			default: System.out.println("Opción equivocada");continue;
			}
			if (artefacto !=null)
				artefactos.add(artefacto);
			
			System.out.print("Mas (S/N)?");
			opcion = teclado.nextLine();
			
		}while (!opcion.toUpperCase().equals("N"));
		
		
	}
	
	public void escribirArtefactosEnFichero(){
		PrintWriter out= null ; 
		try{
			out = new PrintWriter(new FileWriter(nombreFichero));
		
			for (Artefacto a: artefactos){
				if(a instanceof Arma){
					out.print("Arma$");
				}else if(a instanceof Pocion){
					out.print("Pocion$");
				}else if(a instanceof Veneno){
					out.print("Veneno$");
				}
				out.print(a.getId()+"$");
				out.print(a.getNombre()+"$");
				out.print(a.getVidas());
				out.println("");
				
				//out.format("%f\n",a.getNota());
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (out!=null) out.close();
		}
	}
	
	public void escribirCriaturasFichero(){
		PrintWriter out= null ; 
		try{
			out = new PrintWriter(new FileWriter(nombreFicheroCriatura));
			
			for (Criatura c: criaturas){
				out.println(c.escribir());
				
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if (out!=null) out.close();
		}
	}
	
	public void leerCriaturasFichero(String nombreFichero){
		BufferedReader in= null;
		ArrayList<Artefacto> artefactosFich = new ArrayList<>();
		String []valoresCriatura = null, valoresArtefacto = null;
		Artefacto a = null;
		String linea;
		
		Criatura c = null;
		
		try {
			in = new BufferedReader(new FileReader(nombreFicheroCriatura));
			while ((linea = in.readLine()) != null){
				valoresCriatura = linea.split("[$]");				
				if(valoresCriatura[0].equals("Elfo")){
					c = new Elfo(Integer.parseInt(valoresCriatura[1]), valoresCriatura[2]);
					c.setNumVidas(Integer.parseInt(valoresCriatura[3]));
					c.setEstado(Integer.parseInt(valoresCriatura[4]));
				}else if(valoresCriatura[0].equals("Orco")){
					c = new Elfo(Integer.parseInt(valoresCriatura[1]), valoresCriatura[2]);
					c.setNumVidas(Integer.parseInt(valoresCriatura[3]));
					c.setEstado(Integer.parseInt(valoresCriatura[4]));
				}
						valoresArtefacto = valoresCriatura[5].split("[&]");
						for(int i = 0 ; i < valoresArtefacto.length ; i++){
							a = artefactos.get(Integer.parseInt(valoresArtefacto[i]));
							artefactosFich.add(a);
							
							
						}
						c.addArtefacto(artefactosFich);
						criaturas.add(c);
						//tabla.setListaCriaturas(criaturas);
				
			}
		}catch(FileNotFoundException e) {
			System.out.println("No se encuentra fichero criaturas");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void leerArtefactoDeFichero(String nombreFichero){
		BufferedReader in= null;
		String []valoresArtefactos = null;
		String linea;
		Artefacto a = null;
	
			try {
				in = new BufferedReader(new FileReader(nombreFichero));
				while((linea = in.readLine()) != null){
					valoresArtefactos = linea.split("[$]");
					if(valoresArtefactos[0].equals("Arma")){
						a = new Arma(Integer.parseInt(valoresArtefactos[1]), valoresArtefactos[2],Integer.parseInt(valoresArtefactos[3]));
					}else if(valoresArtefactos[0].equals("Veneno")){
						a = new Veneno(Integer.parseInt(valoresArtefactos[1]), valoresArtefactos[2]);
					}else if(valoresArtefactos[0].equals("Pocion")){
						a = new Pocion(Integer.parseInt(valoresArtefactos[1]), valoresArtefactos[2],Integer.parseInt(valoresArtefactos[3]));
					}
					artefactos.add(a);
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("No se encuentra el fichero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(in!=null)
					try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
				
		
			
		
			
		
}


	
	public static void main(String[] args) {
		Juego ejercicio = new Juego();
		ejercicio.leerArtefactoDeFichero(nombreFichero);
		ejercicio.leerCriaturasFichero(nombreFicheroCriatura);
		ejercicio.menu();
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
