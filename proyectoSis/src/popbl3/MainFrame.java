package popbl3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainFrame implements ActionListener{

	JFrame ventana;
	JButton bLogin;
	JLabel lValor;
	JTextField textoUsuario;
	JPasswordField textoPass;
	
	
	public MainFrame(){
		ventana = new JFrame ("Control de acceso");
		//ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ventana.setSize(400,260);
		ventana.setLocation (100,100);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setResizable(false);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createEmptyBorder(40,20,20,20));
		panel.setLayout(null);
		panel.add(crearPanelTextos());
		panel.add(crearPanelBotones());
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon("imagenes/manos-pastel.png"));
		lblImagen.setBounds(0, 0, 394, 231);
		panel.add(lblImagen);
		
		return panel;
	}

	private Component crearPanelTextos() {
		JPanel panel = new JPanel (new GridLayout(2,1,20,20));
		panel.setOpaque(false);
		panel.setBounds(70, 40, 254, 98);
		textoUsuario = new JTextField();
		textoPass = new JPasswordField();
		panel.setBorder(BorderFactory.createTitledBorder(null, "Identificacion", TitledBorder.CENTER, 0, new Font("Algerian", Font.PLAIN, 16), new Color(20,20,20)));
		
		textoUsuario.addActionListener(this);
		textoUsuario.setActionCommand("Login");
		textoPass.addActionListener(this);
		textoPass.setActionCommand("Login");
		
		JLabel username = new JLabel("Username:");
		JLabel password = new JLabel("Password:");
		
		username.setOpaque(true);
		username.setHorizontalAlignment(JLabel.CENTER);
		username.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		password.setOpaque(true);
		password.setHorizontalAlignment(JLabel.CENTER);
		password.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));		
		
		panel.add(username);
		panel.add(textoUsuario);
		panel.add(password);
		panel.add(textoPass);
		
		return panel;
	}
	

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new FlowLayout());
		panel.setOpaque(false);
		panel.setBounds(70, 176, 254, 33);
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));

		bLogin = new JButton("Login");
		bLogin.addActionListener(this);
		bLogin.setActionCommand("Login");

		panel.add(bLogin);
		
		return panel;
	}

	private boolean verificarLogin(String username, String password) {
		HashMap<String,String> usuarios = new HashMap<>();
		String nombreFichero = "files/login.txt";
		
		BufferedReader in = null;
		String linea = null;
		String valores[];
		
		try {
			in = new BufferedReader (new FileReader (nombreFichero));
			while ((linea = in.readLine())!=null){
				valores = linea.split("[=]");
				usuarios.put(valores[0], valores[1]);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		
		if(usuarios.containsKey(username)){
			if(usuarios.get(username).equals(password)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
		
	}
	
	public static Usuario cargarUsuario(String username) {
		///////////////////////////////////////////////////////////////////////////////////////////////////
		final String nombreFicheroPacientes = "files/pacientes.txt";
		final String nombreFicheroFisios = "files/fisioterapeutas.txt";
		final String nombreFicheroAdmins = "files/administradores.txt";
		final String [] nombreFicheros = {nombreFicheroPacientes, nombreFicheroFisios, nombreFicheroAdmins};

		String separador = "$";
		BufferedReader in = null;
		String linea = null;
		String valores[];
		Usuario user;
		
		try {
			
			for(String ficheroUsuario : nombreFicheros){
				
				in = new BufferedReader (new FileReader (ficheroUsuario));
				
				while ((linea = in.readLine())!=null){
					
					valores = linea.split("["+separador+"]");
					if(valores[0].equals(username)){
						
						switch(ficheroUsuario){
							case nombreFicheroPacientes:
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
								
								user = p;
								
								return user;
								
							case nombreFicheroFisios:
								user = new Fisio(valores[0],valores[1],valores[2],
								 				 valores[3],valores[4]);
								return user;
								
							case nombreFicheroAdmins:
								user = new Administrador(valores[0],valores[1],valores[2],
														 valores[3],valores[4]);
								return user;
								
							default:
								break;
						}
					}
				}
				//Si no encuentra el usuario en el fichero, lo cierra y pasa al siguiente
				in.close();
			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username;
		String password;
		
		if(e.getActionCommand().equals("Login")){
			username = textoUsuario.getText();
			password = String.valueOf(textoPass.getPassword());
			
			if(verificarLogin(username, password)){
				Usuario u;
				if((u = cargarUsuario(username))!= null){
					if (u instanceof Paciente){
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									PacienteFrame frame = new PacienteFrame((Paciente)u);									
									//frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
					if (u instanceof Fisio){
						FisioFrame frame = new FisioFrame((Fisio)u);
						frame.setVisible(true);
					}
					if (u instanceof Administrador){
						AdminFrame frame = new AdminFrame((Administrador)u);
						frame.setVisible(true);
					}
					
					ventana.dispose();
					
				}else{
					JOptionPane.showMessageDialog(ventana, "Error al cargar datos de usuario. Contacte con el administrador", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}else{
				JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				textoUsuario.setText(null);
				textoPass.setText(null);
			}
			
		}
		
		
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (UnsupportedLookAndFeelException ex) {
		}
		MainFrame mainframe = new MainFrame();
		

	}
}

