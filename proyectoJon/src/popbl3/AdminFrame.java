package popbl3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AdminFrame extends JFrame implements ListSelectionListener, ChangeListener {
	
	
	private final int ANCHO_PANTALLA = 650;
	private final int LARGO_PANTALLA = 650;
	private final int POS_X_PANTALLA = 50;
	private final int POS_y_PANTALLA = 25;
	private final int FALLO = -1;
	private final int FISIOTERAPEUTA = 0;
	private final int PACIENTE = 1;
	private final int ADMINISTRADOR = 2;
	
	private Administrador admin;
	private MiAction accAñadir;
	private MiAction accEliminar;
	private MiAction accEditar;
	private MiAction accLogOut;
	private JTabbedPane pestañas;
	private JPanel panelDatos;
	private UsuarioRenderer cellRendererUsuario;	
	private ModeloListaUsuario modeloFisios;
	private JList<Usuario> listaFisios;
	private ModeloListaUsuario modeloPaciente;
	private JList<Usuario> listaPacientes;
	private ModeloListaUsuario modeloAdmin;
	private JList<Usuario> listaAdministradores;
	
	public AdminFrame(Administrador admin){
		super (admin.getNombre()+" "+admin.getApellido1()+" "+admin.getApellido2()+" (Admin)");
		this.admin = admin;
		this.crearAcciones();
		this.setSize(ANCHO_PANTALLA,LARGO_PANTALLA);
		this.setLocation(POS_X_PANTALLA,POS_y_PANTALLA);
		this.setJMenuBar(crearBarraMenu());
		this.getContentPane().add(crearToolBar(),BorderLayout.NORTH);
		this.getContentPane().add(crearPanelVentana(),BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void crearAcciones() {
		accAñadir = new MiAction("Añadir", new ImageIcon("iconos/añadir.png"), "Añadir un usuario", KeyEvent.VK_A);
		accEditar = new MiAction("Editar", new ImageIcon("iconos/editar.png"), "Editar un usuario", KeyEvent.VK_M);
		accEliminar = new MiAction("Eliminar", new ImageIcon("iconos/eliminar.png"), "Eliminar un usuario", KeyEvent.VK_E);
		accLogOut = new MiAction("Log out", new ImageIcon("iconos/logOut.png"), "Cerrar la sesión actual", KeyEvent.VK_O);
	}
	
	private JMenuBar crearBarraMenu() {
		JMenuBar barra =  new JMenuBar();
		JMenu editar = new JMenu("Editar");
		JMenu salir = new JMenu("Salir");
		
		editar.add(accAñadir);
		editar.addSeparator();
		editar.add(accEditar);
		editar.addSeparator();
		editar.add(accEliminar);
		
		accEditar.setEnabled(false);
		accEliminar.setEnabled(false);
		
		barra.add(editar);
		barra.add(Box.createHorizontalGlue());
		
		salir.add(accLogOut);
		barra.add (salir);
		
		return barra;	
	}
	
	private Component crearToolBar() {
		JToolBar toolBar = new JToolBar();
		JButton boton;
		
		toolBar.setFloatable(false);
		
		toolBar.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
		boton =(JButton) toolBar.add(new JButton (accAñadir));
		toolBar.addSeparator(new Dimension (40,0));
		boton =(JButton) toolBar.add(new JButton (accEditar));
		toolBar.addSeparator(new Dimension (40,0));
		boton =(JButton) toolBar.add(new JButton (accEliminar));
		
		
		toolBar.add(Box.createHorizontalGlue());
		
		boton =(JButton) toolBar.add(new JButton (accLogOut));
		
		return toolBar;
	}


	private Component crearPanelVentana() {
		int lineas = 1;
		int columnas = 2;
		int distanciaBorder = 10;
		JPanel panel = new JPanel(new BorderLayout(distanciaBorder,distanciaBorder));
		
		panel.add(crearPanelListaUsuarios(), BorderLayout.WEST);
		panel.add(crearPanelDatos(), BorderLayout.CENTER);
		
		panel.setBorder(BorderFactory.createEmptyBorder(distanciaBorder,distanciaBorder,
														distanciaBorder,distanciaBorder));
		return panel;
	}

	private Component crearPanelListaUsuarios() {
		
		pestañas = new JTabbedPane();
		pestañas.setPreferredSize(new Dimension(318,0));
		
		cellRendererUsuario = new UsuarioRenderer();

		pestañas.addTab("Fisioterapeutas", new ImageIcon("iconos/fisio.png"), crearScrollFisio());
        
		pestañas.addTab("Pacientes", new ImageIcon("iconos/paciente.png"), crearScrollPaciente());
        
		pestañas.addTab("Administradores", new ImageIcon("iconos/admin.png"), crearScrollAdmin());
        
        pestañas.addChangeListener(this);
        
		return pestañas;
	}
	
	private Component crearScrollAdmin() {
		JScrollPane panelScrollAdmin = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		modeloAdmin = new ModeloListaUsuario("admin");
		listaAdministradores = new JList<>();
		listaAdministradores.setModel(modeloAdmin);
		listaAdministradores.setCellRenderer(cellRendererUsuario);
		listaAdministradores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaAdministradores.addListSelectionListener(this);
		panelScrollAdmin.setViewportView(listaAdministradores);
		return panelScrollAdmin;
	}

	private Component crearScrollPaciente() {
		JScrollPane panelScrollPaciente = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		modeloPaciente = new ModeloListaUsuario("paciente");
		listaPacientes = new JList<>();
		listaPacientes.setModel(modeloPaciente);
		listaPacientes.setCellRenderer(cellRendererUsuario);
		listaPacientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaPacientes.addListSelectionListener(this);
		panelScrollPaciente.setViewportView(listaPacientes);
		return panelScrollPaciente;
		
	}

	private Component crearScrollFisio(){
		JScrollPane panelScrollFisio = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		modeloFisios = new ModeloListaUsuario("fisio");
		listaFisios = new JList<>();
		listaFisios.setModel(modeloFisios);
		listaFisios.setCellRenderer(cellRendererUsuario);
		listaFisios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaFisios.addListSelectionListener(this);
		panelScrollFisio.setViewportView(listaFisios);
		return panelScrollFisio;
	}
	
	private Component crearPanelDatos() {
		int columnas = 1;
		int filas = 10;
		int separacionX = 0;
		int separacionY = 10;
		int distanciaBorde = 31;
		int sinDistancia = 0;
		panelDatos = new JPanel(new GridLayout(filas,columnas,separacionX,separacionY));
		panelDatos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(distanciaBorde,
																								sinDistancia,
																								sinDistancia,
																								sinDistancia),
							 BorderFactory.createLoweredBevelBorder()));
		return panelDatos;
	}

	

	private class MiAction extends AbstractAction {
		String texto;
		
		public MiAction (String texto, Icon imagen, String descrip, Integer nemonic){
			super(texto,imagen);
			this.texto = texto;
			this.putValue(Action.SHORT_DESCRIPTION ,descrip);
			this.putValue(Action.MNEMONIC_KEY, nemonic);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(texto){
			case "Añadir":
				añadeUsuario();
				break;
				
			case "Editar":
				editaUsuario();
				break;
				
			case "Eliminar":
				eliminaUsuario();
				break;
				
			case "Log out":
				cerrarSesion();
				break;
				
			default:
				JOptionPane.showMessageDialog(AdminFrame.this, "Error en el switch case", "Error", JOptionPane.ERROR_MESSAGE);
				break;	
			}
		}
	}
	
	private void cerrarSesion(){
		MainFrame frame = new MainFrame();
		AdminFrame.this.dispose();
	}
	
	private void eliminaUsuario(){//////////////////////////////
		int resultado = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el usuario?", "Eliminar usuario", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		
		if (resultado == JOptionPane.YES_OPTION){
			int tipoUsuario = pestañas.getSelectedIndex();
			
			switch(tipoUsuario){
				case FISIOTERAPEUTA:
					eliminarUsuarioDelFichero(listaFisios.getSelectedValue(), tipoUsuario);
					eliminarDatosLoginDelUsuario(listaFisios.getSelectedValue());
					modeloFisios.remove(listaFisios.getSelectedIndex());	
					break;
				case PACIENTE:
					eliminarUsuarioDelFichero(listaPacientes.getSelectedValue(), tipoUsuario);
					eliminarDatosLoginDelUsuario(listaPacientes.getSelectedValue());
					modeloPaciente.remove(listaPacientes.getSelectedIndex());		
					break;
				case ADMINISTRADOR:
					eliminarUsuarioDelFichero(listaAdministradores.getSelectedValue(), tipoUsuario);
					eliminarDatosLoginDelUsuario(listaAdministradores.getSelectedValue());
					modeloAdmin.remove(listaAdministradores.getSelectedIndex());
					break;
				default:
					break;
			}
			
			estadoBtEditDelete(false);
		}
	}///////////////////////////////////////////////////////////

	private void eliminarUsuarioDelFichero(Usuario usuarioABorrar, int tipoUsuario) {//////////////
		PrintWriter out = null;
		String nombreFichero = null;
		
		BufferedReader in = null;
		String linea = null;
		String valores[];
		String separador = "$";
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		
		switch(tipoUsuario){
		case FISIOTERAPEUTA:
			nombreFichero = "files/fisioterapeutas.txt";
			break;
		case PACIENTE:
			nombreFichero = "files/pacientes.txt";
			break;
		case ADMINISTRADOR:
			nombreFichero = "files/administradores.txt";
			break;
		}		
		
		try {
			in = new BufferedReader (new FileReader(nombreFichero));
			
			while ((linea = in.readLine())!=null){
				valores = linea.split("["+separador+"]");
				
					switch (tipoUsuario) {
					case FISIOTERAPEUTA:
						Fisio f = new Fisio(valores[0],valores[1],
								valores[2],valores[3],valores[4]);
						listaUsuarios.add(f);
						break;
					case ADMINISTRADOR:
						Administrador a = new Administrador(valores[0],valores[1],
								valores[2],valores[3],valores[4]);
						listaUsuarios.add(a);
						break;
					case PACIENTE:
						Paciente p = new Paciente(valores[0],valores[1],valores[2],
								valores[3],valores[4],valores[5],valores[6]);
						ArrayList<Ejercicio> ejercicios = new ArrayList<>();
						int numEjercicios = Integer.valueOf(valores[7]);
						
						for (int i = 0; i < numEjercicios ; i++){
							linea = in.readLine();
							valores = linea.split("["+separador+"]");
							ejercicios.add(new Ejercicio(valores[0],valores[1],valores[2],valores[3],
														 valores[4],valores[5],Integer.valueOf(valores[6]),
														 Boolean.valueOf(valores[7])));
						}
						p.setEjercicios(ejercicios);
						
						listaUsuarios.add(p);
						break;
					default:
						break;
					}
			}
			
			out = new PrintWriter(nombreFichero); 
			
			for(Usuario usuarioNormal : listaUsuarios){
				if(usuarioNormal.getUserName().equals(usuarioABorrar.getUserName())){
					//No hace nada, por lo que ese usuario se borra de la lista
				}else{
					out.println(usuarioNormal.guardar());
					if(tipoUsuario == PACIENTE){
						Paciente paciente = (Paciente)usuarioNormal;
						for(Ejercicio ejercicio : paciente.getEjercicios()){
							out.println(ejercicio.guardar());
						}
					}
				}				
			}
			
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close();} catch (IOException e) {}
			}
			out.close();
		}
		
	}/////////////////////////////////////////////
	
	private void eliminarDatosLoginDelUsuario(Usuario usuarioABorrar) {
		HashMap<String,String> usuarios = new HashMap<>();
		String nombreFichero = "files/login.txt";
	    String linea = null;
		
		BufferedReader in = null;
		String valores[];
		
		PrintWriter out = null;
		String separador = "=";

	        try {
	        	in = new BufferedReader (new FileReader (nombreFichero));
	            while ((linea = in.readLine()) != null) {
	            	valores = linea.split("[=]");
					usuarios.put(valores[0], valores[1]);
	            }

	            out = new PrintWriter(nombreFichero);
	            
	            for (Map.Entry<String, String> pareja : usuarios.entrySet()) {
	                String key = pareja.getKey();
	                String value = pareja.getValue();
	               
	                if(key.equals(usuarioABorrar.getUserName())){
	                	//No hace nada, por lo que ese usuario se borra de la lista
	                }else{
	                	out.println(key+separador+value);
	                }
	            }
	           
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }finally{
	        	try {
					if (in != null) {
						in.close();
					}
	        	} catch (IOException e) {}
	        	
				if (out != null) {
					out.close();
				}
	        }
	}////////////////////////////////////////////////////////

	private void añadeUsuario(){////////////////////////////////////////////
		int tipoUsuario;
		tipoUsuario = pestañas.getSelectedIndex();
		
		switch (tipoUsuario) {
		case FISIOTERAPEUTA:
			Fisio nuevoFisio = null;
			DialogoUsuario dialogoFisio = new DialogoUsuario(this, nuevoFisio, tipoUsuario);
			nuevoFisio = (Fisio)dialogoFisio.getUsuario();
			if(nuevoFisio != null)
				modeloFisios.addElement(nuevoFisio);
			
			break;
		case PACIENTE:
			Paciente nuevoPaciente = null;
			DialogoUsuario dialogoPaciente = new DialogoUsuario(this, nuevoPaciente, tipoUsuario);
			nuevoPaciente = (Paciente)dialogoPaciente.getUsuario();
			if(nuevoPaciente != null)
				modeloPaciente.addElement(nuevoPaciente);
			break;
		case ADMINISTRADOR:
			Administrador nuevoAdmin = null;
			DialogoUsuario dialogoAdmin = new DialogoUsuario(this, nuevoAdmin, tipoUsuario);
			nuevoAdmin = (Administrador)dialogoAdmin.getUsuario();
			if(nuevoAdmin != null)
				modeloAdmin.addElement(nuevoAdmin);
			break;
		default:
			break;
		}
		
	}///////////////////////////////////////////////////////
	
	private void editaUsuario(){////////////////////////////
		int tipoUsuario;
		int indice;
		
		tipoUsuario = pestañas.getSelectedIndex();
		switch (tipoUsuario) {
		case FISIOTERAPEUTA:
			Fisio fisioNuevo;
			DialogoUsuario dialogoFisio = new DialogoUsuario(this, listaFisios.getSelectedValue(), tipoUsuario);
			fisioNuevo = (Fisio) dialogoFisio.getUsuario();
			if (fisioNuevo != null) {
				modeloFisios.setElementAt(fisioNuevo, listaFisios.getSelectedIndex());
				crearLabelsDatos(fisioNuevo);
				this.repaint();

			}
			break;
		case PACIENTE:
			Paciente pacienteNuevo;
			DialogoUsuario dialogoPaciente = new DialogoUsuario(this, listaPacientes.getSelectedValue(), tipoUsuario);
			pacienteNuevo = (Paciente) dialogoPaciente.getUsuario();
			if (pacienteNuevo != null) {
				modeloPaciente.setElementAt(pacienteNuevo, listaPacientes.getSelectedIndex());
				crearLabelsDatos(pacienteNuevo);
				this.repaint();

			}
			break;
		case ADMINISTRADOR:
			Administrador adminNuevo;
			DialogoUsuario dialogoAdmin = new DialogoUsuario(this, listaAdministradores.getSelectedValue(), tipoUsuario);
			adminNuevo = (Administrador) dialogoAdmin.getUsuario();
			if (adminNuevo != null) {
				modeloAdmin.setElementAt(adminNuevo, listaAdministradores.getSelectedIndex());
				crearLabelsDatos(adminNuevo);
				this.repaint();

			}
			break;
		default:
			break;
		}
	}//////////////////////////////////////////////////////
	
	private void estadoBtEditDelete(boolean estado){
		accEditar.setEnabled(estado);
		accEliminar.setEnabled(estado);
	}
	
	private void limpiarPanelDatos(){
		panelDatos.removeAll();
		panelDatos.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		if (!e.getValueIsAdjusting()) {
			
			estadoBtEditDelete(true);
			int numPestaña = pestañas.getSelectedIndex();
			
			switch(numPestaña){
				case FISIOTERAPEUTA:
					crearLabelsDatos(listaFisios.getSelectedValue());
					break;
				case PACIENTE:
					crearLabelsDatos(listaPacientes.getSelectedValue());
					break;
				case ADMINISTRADOR:
					crearLabelsDatos(listaAdministradores.getSelectedValue());
					break;
				default:
					System.out.println("Ventana equivocada");
					break;
							
			}
		}
	}

	private void crearLabelsDatos(Usuario usuario) {
		JLabel label;
		
		limpiarPanelDatos();
		
		if(usuario != null){
			
			label = new JLabel();
			label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Nombre"));
			panelDatos.add(label);
			label.setText(usuario.getNombre());
			
			label = new JLabel();
			panelDatos.add(label);
			label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Apellido1"));
			label.setText(usuario.getApellido1());
			
			label = new JLabel();
			panelDatos.add(label);
			label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Apellido2"));
			label.setText(usuario.getApellido2());
			
			label = new JLabel();
			panelDatos.add(label);
			label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Username"));
			label.setText(usuario.getUserName());
			
			label = new JLabel();
			panelDatos.add(label);
			label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Centro"));
			label.setText(usuario.getCentro());
			
			if(usuario instanceof Paciente){
				label = new JLabel();
				panelDatos.add(label);
				label.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.cyan), "Tipo de lesión"));
				Paciente p = (Paciente) usuario;
				label.setText(p.getTipoLesion());
			}
			
		}		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		listaAdministradores.clearSelection();
		listaPacientes.clearSelection();
		listaFisios.clearSelection();
		estadoBtEditDelete(false);///////////////////////////////////////////////////
		
		
	}	
}
