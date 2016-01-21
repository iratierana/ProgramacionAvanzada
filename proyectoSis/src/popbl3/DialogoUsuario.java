package popbl3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sound.midi.spi.MidiFileReader;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class DialogoUsuario extends JDialog implements ActionListener, MouseListener, ItemListener	{
	
	private final int FISIO = 0;
	private final int PACIENTE = 1;
	private final int ADMIN = 2;
	
	private int tipoUsuario;
	
	JTextField nombre;
	JTextField apellido1;
	JTextField apellido2;
	JTextField centro;
	JTextField username;
	JPasswordField password;
	
	Usuario usuario;
	String contraseñaNueva;
	String usernameAntiguo;
	
	private ModeloEjercicioEstandar modelo;
	private JComboBox<String> comboBox;
	private JTextField usernameFisio;
	private JList<Ejercicio> list;
	private JScrollPane sPanel;
	
	public DialogoUsuario(JFrame panelAnterior, Usuario usuario, int tipoUsuario){
		super(panelAnterior,usuario == null ? "Añade Usuario": "Modifica Usuario");
		super.setModal(true);
		
		this.tipoUsuario = tipoUsuario;
		if(usuario == null) {
			this.usuario = null;
		}else{
			this.usuario = usuario;
			this.usernameAntiguo = usuario.getUserName();
			this.contraseñaNueva = null;
		}
		
		if(tipoUsuario == PACIENTE){
			this.setSize(640, 500);
		}else{
			this.setSize(350, 500);
		}
		
		this.setLocation(200,50);
		this.setContentPane(crearPanelVentana());
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.add(crearPanelSeparador(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelSeparador() {
		JPanel panel = new JPanel();
		
		if(tipoUsuario == PACIENTE){
			panel.setLayout(new GridLayout(1, 2, 20, 0));
			panel.add(crearPanelDatos());
			panel.add(crearPanelDatosPaciente());
		}else{
			panel.setLayout(new GridLayout(1, 1));
			panel.add(crearPanelDatos());
		}
		
		
		return panel;
	}

	private Component crearPanelDatos() {
		JPanel panel = new JPanel (new GridLayout(7,1,0,10));
		nombre = new JTextField(20);
		apellido1 = new JTextField(20);
		apellido2 = new JTextField(20);
		centro = new JTextField(20);
		username = new JTextField(20);
		password = new JPasswordField();
		
		nombre.setText(usuario == null ? "": usuario.getNombre());
		apellido1.setText(usuario == null ? "": usuario.getApellido1());
		apellido2.setText(usuario == null ? "": usuario.getApellido2());
		centro.setText(usuario == null ? "": usuario.getCentro());
		username.setText(usuario == null ? "": usuario.getUserName());
		
		panel.add(crearTextField(nombre,"Nombre"));
		panel.add(crearTextField(apellido1,"Primer Apellido"));
		panel.add(crearTextField(apellido2,"Segundo Apellido"));
		panel.add(crearTextField(centro,"Centro"));
		panel.add(crearTextField(username,"Nombre de usuario"));
		if(usuario == null){
			panel.add(crearTextField(password,"Contraseña"));
		}else{
			JButton boton = new JButton("¿Cambiar la contraseña?");
			boton.addActionListener(this);
			boton.setActionCommand("Nueva Contraseña");
			panel.add(boton);
		}		
		return panel;
	}

	

	private Component crearTextField(JTextField text, String titulo) {
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), titulo));
		
		panel.add(text);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new GridLayout(1,2,20,0));
		JButton bOk = new JButton ("OK");
		bOk.setActionCommand("ok");
		bOk.addActionListener(this);
		
		JButton bCancel = new JButton ("Cancelar");
		bCancel.setActionCommand("cancel");
		bCancel.addActionListener(this);
		
		panel.add(bOk);
		panel.add(bCancel);
		return panel;
	}
	
	private Component crearPanelDatosPaciente() {
		JPanel panel = new JPanel(new BorderLayout(0, 10));
		
		panel.add(crearPanelLesion(), BorderLayout.NORTH);
		panel.add(crearPanelEjercicios(), BorderLayout.CENTER);
		panel.add(crearPanelFisio(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Component crearPanelLesion() {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), "Lesión"));
		Paciente paciente  = (Paciente)usuario;
		comboBox = new JComboBox<>();
		//comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Primera Falange", "Segunda Falange", "Tercera Falange", "Cuarta Falange", "Quinta Falange"}));
		comboBox.setModel(new DefaultComboBoxModel<String>(cargarLesiones()));
		comboBox.setSelectedItem(paciente == null ? null : paciente.getTipoLesion());
		comboBox.addItemListener(this);

		panel.add(comboBox);
		
		return panel;
	}


	private String[] cargarLesiones() {
		String nombreFichero = "files/lesiones.txt";
		String separador = "[$]";
		BufferedReader in = null;
		String linea = null;
		String valores[];
		
		String lesiones[];
		
		ArrayList<String> listaLesiones = new ArrayList<>();
		
		try {
			in = new BufferedReader (new FileReader(nombreFichero));
			while ((linea = in.readLine())!=null){
				valores = linea.split(separador);
				
				listaLesiones.add(valores[0]);

			}
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close();} catch (IOException e) {}
			}
		}
		
		lesiones = new String[listaLesiones.size()];
		
		int i = 0;
		for(String lesion : listaLesiones){
			lesiones[i] = lesion;
			i++;
		}
		
		return lesiones;
	}


	private Component crearPanelEjercicios() {
		sPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		list = new JList<>();
		modelo = new ModeloEjercicioEstandar((String)comboBox.getSelectedItem());
		list.setModel(modelo);
		list.setCellRenderer(new EjercicioListRender());
		list.addMouseListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		if(usuario != null){
			marcarEjercicios();
		}
		sPanel.setViewportView(list);
		
		return sPanel;
	}


	private void marcarEjercicios() {
		Paciente paciente  = (Paciente)usuario;
		/*ArrayList<String> ids = new ArrayList<>();
		
		for(Ejercicio ejercicioPaciente : paciente.getEjercicios()){
			ids.add(ejercicioPaciente.getId());
		}
		
		for(int i = 0; i < modelo.getSize(); i++){
			if(ids.contains(modelo.getElementAt(i).getId())){
				modelo.getElementAt(i).setSeleccionado(true);
			}
		}	*/
		
		
		for(Ejercicio ejercicio : paciente.getEjercicios()){
			for(int i = 0; i < modelo.getSize(); i++){
				if(ejercicio.getId().equals(modelo.getElementAt(i).getId())){
					modelo.setElementAt(ejercicio, i);
				}
			}
		}
		
	}


	private Component crearPanelFisio() {
		JPanel panel = new JPanel(new GridLayout(1, 1));
		Paciente paciente  = (Paciente)usuario;
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.cyan), "Username del fisioterapeuta"));
		
		usernameFisio = new JTextField(20);
		usernameFisio.setText(paciente == null ? "" : paciente.getFisioAsociado());
		panel.add(usernameFisio);
		
		return panel;
	}


	private void comprobacion() throws HayCamposIncompletos, UsernameRepetido, UsernameDeFisioIncorrecto{
		if(nombre.getText().equals("")) throw new HayCamposIncompletos();
		if(apellido1.getText().equals("")) throw new HayCamposIncompletos();
		if(apellido2.getText().equals("")) throw new HayCamposIncompletos();
		if(centro.getText().equals("")) throw new HayCamposIncompletos();
		if(username.getText().equals("")) throw new HayCamposIncompletos();
		
		if(usuario == null){
			if(String.valueOf(password.getPassword()).equals("")) throw new HayCamposIncompletos();
			if(isUsernameInLoginFile(username.getText())) throw new UsernameRepetido();
		}
		if(usuario != null && usernameAntiguo.equals(username.getText()) == false){
			if(isUsernameInLoginFile(username.getText())) throw new UsernameRepetido();
		}
		
		if(tipoUsuario == PACIENTE){
			if(comboBox.getSelectedItem() == null) throw new HayCamposIncompletos();
			if(isListaEjerciciosEmpty()) throw new HayCamposIncompletos();
			if(usernameFisio.getText().equals("")) throw new HayCamposIncompletos();
			if(fisioUsernameDoesNotExist(usernameFisio.getText())) throw new UsernameDeFisioIncorrecto();
		}
	}

	private class HayCamposIncompletos extends Exception{
		
		public HayCamposIncompletos(){
			super();
		}
		
	}
	
	private class UsernameRepetido extends Exception{
		
		public UsernameRepetido(){
			super();
		}
		
	}
	
	private class UsernameDeFisioIncorrecto extends Exception{
		
		public UsernameDeFisioIncorrecto(){
			super();
		}
		
	}
	
	private boolean isUsernameInLoginFile(String username) {
		HashMap<String,String> usuarios = new HashMap<>();
		String nombreFichero = "files/login.txt";
	    String linea = null;
		
		BufferedReader in = null;
		String valores[];

        try {
        	in = new BufferedReader (new FileReader (nombreFichero));
            while ((linea = in.readLine()) != null) {
            	valores = linea.split("[=]");
				usuarios.put(valores[0], valores[1]);
            }

            if(usuarios.containsKey(username)){
            	return true;
            }else{
            	return false;
            }
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
        	try {
				if (in != null) {
					in.close();
				}
        	} catch (IOException e) {}
        	
        }
		return false;
	}

	private boolean isListaEjerciciosEmpty() {
		for(int i = 0; i < modelo.getSize(); i++){
			if(modelo.getElementAt(i).isSeleccionado()){
				return false;
			}
		}
		return true;
	}
	
	private boolean fisioUsernameDoesNotExist(String usernameFisio) {
		String nombreFichero = "files/fisioterapeutas.txt";
		String separador = "[$]";
		BufferedReader in = null;
		String linea = null;
		String valores[];
		
		try {
			in = new BufferedReader (new FileReader(nombreFichero));
			while ((linea = in.readLine())!=null){
				valores = linea.split(separador);
				
				if(valores[0].equals(usernameFisio)){
					return false;
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
		return true;
	}

	private void añadirUserYPasswordEnFicheroLogin(String user, String password) {
		BufferedWriter out = null;
		String separador = "=";
		try {
			
			out = new BufferedWriter(new FileWriter("files/login.txt", true)); 
			out.write(user+separador+password);
			out.newLine();
	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	private void añadirAlFichero(Usuario user) {
		BufferedWriter out = null;
		String nombreFichero = null;
		
		switch(tipoUsuario){
		case FISIO:
			nombreFichero = "files/fisioterapeutas.txt";
			break;
		case PACIENTE:
			nombreFichero = "files/pacientes.txt";
			break;
		case ADMIN:
			nombreFichero = "files/administradores.txt";
			break;
		}
		
		try {
			
			out = new BufferedWriter(new FileWriter(nombreFichero, true)); 
			out.write(user.guardar());
			out.newLine();
			
			if(tipoUsuario == PACIENTE){
				Paciente paciente = (Paciente)user;
				for(Ejercicio ejercicio : paciente.getEjercicios()){
					out.write(ejercicio.guardar());
					out.newLine();
				}
			}
	
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.close();
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}


	private void modificarFicheroLogin(String username, String contraseñaNueva) {
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
	               
	                if(key.equals(usernameAntiguo)){
	                	if(contraseñaNueva == null){
	                		out.println(username+separador+value);
	                	}else{
	                		out.println(username+separador+contraseñaNueva);
	                	}
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


	private void modificarDatosFichero(Usuario usuarioModificado) {
		PrintWriter out = null;
		String nombreFichero = null;
		
		BufferedReader in = null;
		String linea = null;
		String valores[];
		String separador = "$";
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		
		switch(tipoUsuario){
		case FISIO:
			nombreFichero = "files/fisioterapeutas.txt";
			break;
		case PACIENTE:
			nombreFichero = "files/pacientes.txt";
			break;
		case ADMIN:
			nombreFichero = "files/administradores.txt";
			break;
		}		
		
		try {
			in = new BufferedReader (new FileReader(nombreFichero));
			
			while ((linea = in.readLine())!=null){
				valores = linea.split("["+separador+"]");
				
					switch (tipoUsuario) {
					case FISIO:
						Fisio f = new Fisio(valores[0],valores[1],
								valores[2],valores[3],valores[4]);
						listaUsuarios.add(f);
						break;
					case ADMIN:
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
				if(usuarioNormal.getUserName().equals(usernameAntiguo)){
					out.println(usuarioModificado.guardar());
					
					if(tipoUsuario == PACIENTE){
						Paciente paciente = (Paciente)usuarioModificado;
						for(Ejercicio ejercicio : paciente.getEjercicios()){
							out.println(ejercicio.guardar());
						}
					}
					
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
		
	}


	public Usuario getUsuario(){
		return usuario;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("ok")){
			try{
				comprobacion();
				
				switch(tipoUsuario){
				
				case FISIO:
					
					if(usuario == null){
						añadirUserYPasswordEnFicheroLogin(username.getText(),String.valueOf(password.getPassword()));
						usuario = new Fisio(username.getText(),
								nombre.getText(),
								apellido1.getText(),
								apellido2.getText(),
								centro.getText());
						añadirAlFichero(usuario);
						
					}else{
						modificarFicheroLogin(username.getText(),contraseñaNueva);
						usuario = new Fisio(username.getText(),
								nombre.getText(),
								apellido1.getText(),
								apellido2.getText(),
								centro.getText());
						modificarDatosFichero(usuario);
					}
					break;
					
				case PACIENTE:
					
					if(usuario == null){
						añadirUserYPasswordEnFicheroLogin(username.getText(),String.valueOf(password.getPassword()));
						
						ArrayList<Ejercicio> ejercicios = new ArrayList<>();
						
						for(int i = 0; i < list.getModel().getSize(); i++){
							if(modelo.getElementAt(i).isSeleccionado())
								ejercicios.add(modelo.get(i));
						}
						
						usuario = new Paciente(username.getText(),
								  nombre.getText(),
								  apellido1.getText(),
								  apellido2.getText(),
								  centro.getText(),
								  String.valueOf(comboBox.getSelectedItem()),
								  ejercicios,
								  usernameFisio.getText());
						
						añadirAlFichero(usuario);
						
					}else{
						modificarFicheroLogin(username.getText(),contraseñaNueva);
						
						ArrayList<Ejercicio> ejercicios = new ArrayList<>();
						
						for(int i = 0; i < list.getModel().getSize(); i++){
							if(modelo.getElementAt(i).isSeleccionado())
								ejercicios.add(modelo.get(i));
						}
						
						usuario = new Paciente(username.getText(),
								  nombre.getText(),
								  apellido1.getText(),
								  apellido2.getText(),
								  centro.getText(),
								  String.valueOf(comboBox.getSelectedItem()),
								  ejercicios,
								  usernameFisio.getText());
						
						modificarDatosFichero(usuario);
					}
					break;
					
				case ADMIN:
					
					if(usuario == null){
						añadirUserYPasswordEnFicheroLogin(username.getText(),String.valueOf(password.getPassword()));
						usuario = new Administrador(username.getText(),
								nombre.getText(),
								apellido1.getText(),
								apellido2.getText(),
								centro.getText());
						añadirAlFichero(usuario);
						
					}else{
						modificarFicheroLogin(username.getText(),contraseñaNueva);
						usuario = new Administrador(username.getText(),
								nombre.getText(),
								apellido1.getText(),
								apellido2.getText(),
								centro.getText());
						modificarDatosFichero(usuario);
					}
					break;
				default:
					System.out.println("Error en Action Performed de OK");
					break;
				}
				this.dispose();
				
			}catch(HayCamposIncompletos e1){
				JOptionPane.showMessageDialog(this,"Rellene todos los campos", "Error",JOptionPane.ERROR_MESSAGE);
			}catch(UsernameRepetido e1){
				JOptionPane.showMessageDialog(this,"El username escrito ya está en uso, cámbielo", "Error",JOptionPane.ERROR_MESSAGE);
			}catch(UsernameDeFisioIncorrecto e1){
				JOptionPane.showMessageDialog(this,"El username del fisioterapeuta es incorrecto, cámbielo", "Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getActionCommand().equals("cancel")){
			dispose();
		}
		
		if(e.getActionCommand().equals("Nueva Contraseña")){
			DialogoContraseñaNueva dialogo = new DialogoContraseñaNueva(this);
			contraseñaNueva = dialogo.getNewContraseña();
			
		}
		
			
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){//Button1 = Boton izquierdo
			if(modelo.isInicializado()){
				int index = list.locationToIndex(e.getPoint());
		        Ejercicio ejercicio = modelo.getElementAt(index);
		        ejercicio.changeSelection();
		        list.repaint();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }


	@Override
	public void itemStateChanged(ItemEvent arg0) {
		modelo = new ModeloEjercicioEstandar((String)comboBox.getSelectedItem());
		list.setModel(modelo);
		
		if(usuario != null){
			marcarEjercicios();
		}
		sPanel.setViewportView(list);
		
	}

	
}

