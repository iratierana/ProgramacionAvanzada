package popbl3;

import java.awt.BorderLayout;
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

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FisioFrame extends JFrame implements ListSelectionListener {

	private final static int FALLO = -1;
	private final String tipoUsuario = "paciente";
	private JPanel contentPane;
	
	Fisio fisio;
	
	ModeloListaUsuario modelo;
	private UsuarioRenderer cellRendererUsuario;
	private JScrollPane scrollPane_1;
	private JList<Usuario> list;
	
	MiAction accAñadir;
	MiAction accEliminar;
	MiAction accEditar;
	MiAction accLogOut;
	
	JTable tablaEjercicios;
	ModeloTablaEjercicio modeloTabla;
	TrazadorTablaEjercicios trazadorTabla;
	ModeloColumnasTablaEjercicios modeloColumnasTabla;
	JScrollPane scrollPaneTabla;
	

	public FisioFrame(Fisio fisio) {
		this.setTitle(fisio.getNombre()+" "+fisio.getApellido1()+" "+fisio.getApellido2()+" (Fisioterapeuta)");
		this.fisio = fisio;
		this.crearAcciones();	
		this.setSize(800, 600);
		this.setLocation(100, 100);
		this.setJMenuBar(crearBarraMenu());
		this.getContentPane().add(crearToolBar(),BorderLayout.NORTH);
		this.getContentPane().add(crearPanelCentro(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
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
	
	private Component crearPanelCentro() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel.add(crearPanelListaUsuarios(), BorderLayout.WEST);
		panel.add(crearPanelTablaEjercicios(), BorderLayout.CENTER);
		
		return panel;
	}

	private Component crearPanelListaUsuarios() {
		JScrollPane panelScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelScroll.setPreferredSize(new Dimension(200,0));
		
		cellRendererUsuario = new UsuarioRenderer();
		modelo = new ModeloListaUsuario(tipoUsuario, this.fisio);
		list = new JList<Usuario>();
		list.setModel(modelo);
		list.setCellRenderer(cellRendererUsuario);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		panelScroll.setViewportView(list);
		
		return panelScroll;
	}

	private Component crearPanelTablaEjercicios() {
		scrollPaneTabla = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
		trazadorTabla = new TrazadorTablaEjercicios();
		modeloColumnasTabla = new ModeloColumnasTablaEjercicios(trazadorTabla);
		
		return scrollPaneTabla;
	}

	private void crearAcciones() {
		accAñadir = new MiAction("Añadir", new ImageIcon("iconos/añadir.png"), "Añadir un paciente", KeyEvent.VK_A);
		accEditar = new MiAction("Editar", new ImageIcon("iconos/editar.png"), "Editar un paciente", KeyEvent.VK_M);
		accEliminar = new MiAction("Eliminar", new ImageIcon("iconos/eliminar.png"), "Eliminar un paciente", KeyEvent.VK_E);
		accLogOut = new MiAction("Log out", new ImageIcon("iconos/logOut.png"), "Cerrar la sesión actual", KeyEvent.VK_O);
	}
	
	private void cerrarSesion(){
		
		MainFrame frame = new MainFrame();
		this.dispose();
	}
	
	private void añade(){
		int tipoUsuarioPaciente = 1;
		Paciente nuevoPaciente = null;
		DialogoUsuario dialogoPaciente = new DialogoUsuario(this, nuevoPaciente, tipoUsuarioPaciente);
		nuevoPaciente = (Paciente)dialogoPaciente.getUsuario();
		if(nuevoPaciente != null)
			modelo.addElement(nuevoPaciente);
	}
	
	private void elimina(){
		
		int reply = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar al paciente?", "Eliminar paciente",
			JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (reply == JOptionPane.YES_OPTION){
			eliminarUsuarioDelFichero(list.getSelectedValue());
			eliminarDatosLoginDelUsuario(list.getSelectedValue());
			modelo.remove(list.getSelectedIndex());
			
			modeloTabla.eliminarListaEjercicios();
			scrollPaneTabla.setViewportView(null);
			accEditar.setEnabled(false);
			accEliminar.setEnabled(false);
		}
	}
	
	private void eliminarUsuarioDelFichero(Usuario usuarioABorrar) {
		PrintWriter out = null;
		String nombreFichero = "files/pacientes.txt";
		
		BufferedReader in = null;
		String linea = null;
		String valores[];
		String separador = "$";
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		
		try {
			in = new BufferedReader (new FileReader(nombreFichero));
			
			while ((linea = in.readLine())!=null){
				valores = linea.split("["+separador+"]");
				
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
				
			}
			
			out = new PrintWriter(nombreFichero); 
			
			for(Usuario usuarioNormal : listaUsuarios){
				if(usuarioNormal.getUserName().equals(usuarioABorrar.getUserName())){
					//No hace nada, por lo que ese usuario se borra de la lista
				}else{
					out.println(usuarioNormal.guardar());
					
					Paciente paciente = (Paciente)usuarioNormal;
					for(Ejercicio ejercicio : paciente.getEjercicios()){
						out.println(ejercicio.guardar());
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
	}
	
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
	}
	
	private void modifica(){
		int tipoUsuarioPaciente = 1;
		Paciente pacienteNuevo;
		
		DialogoUsuario dialogoPaciente = new DialogoUsuario(this, list.getSelectedValue(), tipoUsuarioPaciente);
		pacienteNuevo = (Paciente) dialogoPaciente.getUsuario();
		if(pacienteNuevo != null){
			modelo.setElementAt(pacienteNuevo, list.getSelectedIndex());
			modeloTabla.insertarListaEjercicios(pacienteNuevo.getEjercicios());
			this.repaint();
		}
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
				añade();
				break;
			case "Editar":
				modifica();
				break;
			case "Eliminar":
				elimina();
				break;
			case "Log out":
				cerrarSesion();
				break;
			default:
				JOptionPane.showMessageDialog(FisioFrame.this, "Error en el switch case", "Error", JOptionPane.ERROR_MESSAGE);
				break;
					
			}
		}
	}
	
	private void crearTabla(Paciente paciente) {
		modeloTabla = new ModeloTablaEjercicio(modeloColumnasTabla, paciente);
		tablaEjercicios = new JTable(modeloTabla,modeloColumnasTabla);
		tablaEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaEjercicios.setFillsViewportHeight(true);
		tablaEjercicios.getTableHeader().setReorderingAllowed(false);
		scrollPaneTabla.setViewportView(tablaEjercicios);
		
	}

	@Override
	public void valueChanged(ListSelectionEvent evento) {
		if(!evento.getValueIsAdjusting()){
			accEditar.setEnabled(true);
			accEliminar.setEnabled(true);
			
			if(list.getSelectedValue() != null){
				crearTabla((Paciente)list.getSelectedValue());
			}
		}
		
		
	}
}
