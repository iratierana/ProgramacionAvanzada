package edu.mondragon.pa.tablamodelCambios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PantallaAlumno  extends JDialog implements ActionListener, ItemListener {
	
	final static int ANCHOPANTALLA = 640;
	final static int LARGOPANTALLA = 480;
	final static int POSPANTALLAX = 100;
	final static int POSPANTALLAY = 100;
	boolean sexo;
	ButtonGroup grupo;
	JRadioButton rHombre,rMujer;
	JPanel panelPricipal, panelSecundario, panelTerciario;
	JButton ok, cancelar;
	JTextField jtName, jtPrimerApellido, jtSegundoApellido, jtPueblo, jtEdad;
	
	
	public PantallaAlumno(JFrame principal){
		super(principal, "Añade Alumno");
		panelPricipal = new JPanel(new BorderLayout());
		panelSecundario = new JPanel(new GridLayout(6,1));
		panelTerciario = new JPanel(new GridLayout(1,2));
		ok = new JButton("ok");
		cancelar = new JButton("cancelar");
		jtName = new JTextField("");
		jtPrimerApellido = new JTextField("");
		jtSegundoApellido = new JTextField("");
		jtPueblo = new JTextField("");
		jtEdad = new JTextField("");
		
		this.setSize(ANCHOPANTALLA, LARGOPANTALLA);
		this.setResizable(false);
		this.setLocation(POSPANTALLAX, POSPANTALLAY);
		agregarListeners();
		llenaPanel();
		this.setModal(true);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void llenaPanel() {
		panelSecundario.add(creaTextField("Nombre", jtName));
		panelSecundario.add(creaTextField("Primer Apellido", jtPrimerApellido));
		panelSecundario.add(creaTextField("Segundo Apellido", jtSegundoApellido));
		panelSecundario.add(creaTextField("Pueblo", jtPueblo));
		panelSecundario.add(creaTextField("Edad", jtEdad));
		panelSecundario.add(creaPanelSexo());
		panelSecundario.setBorder(BorderFactory.createEmptyBorder(10, 20, 50, 20));
		panelPricipal.add(panelSecundario, BorderLayout.CENTER);
		panelTerciario.add(creaBoton(ok));
		panelTerciario.add(creaBoton(cancelar));
		panelTerciario.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panelPricipal.add(panelTerciario, BorderLayout.SOUTH);
		panelPricipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(panelPricipal);
	}

	private Component creaPanelSexo() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 0));
		grupo = new ButtonGroup();
		rHombre = new JRadioButton ("Hombre");
		rHombre.setSelected(true);
		rHombre.addItemListener(this);
		
		rMujer = new JRadioButton ("Mujer");
		rMujer.setSelected(false);
		rMujer.addItemListener (this);
		
		panel.add(rHombre);
		panel.add(rMujer);
		
		grupo.add(rHombre);
		grupo.add(rMujer);

		return panel;
	}

	private Component creaBoton(JButton boton) {
		JPanel panel = new JPanel();
		panel.add(boton);
		return panel;
	}

	private Component creaTextField(String titulo, JTextField text) {
		JPanel panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLoweredBevelBorder(), titulo));
		panel.add(text);
		return panel;
	}

	private void agregarListeners() {
		ok.addActionListener(this);
		cancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getActionCommand().equals("ok")){
			if(comprobacion())
				this.dispose();
			else
				JOptionPane.showMessageDialog(this,"Rellene todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);	
		}
		if(evento.getActionCommand().equals("cancelar")){
			jtName.setText("");
			jtPrimerApellido.setText("");
			jtSegundoApellido.setText("");
			this.dispose();
		}
		
	}

	private boolean comprobacion() {
		if(0!=jtName.getText().compareTo("")){
			if(0!=jtPrimerApellido.getText().compareTo("")){
				if(0!=jtSegundoApellido.getText().compareTo("")){
					return true;
				}
			}
		}
		return false;
	}

	
	public Alumno getAlumno(){
		Alumno nuevo = null;
		if(jtName.getText().compareTo("")==0){
			nuevo = null;
		}
		if(jtPrimerApellido.getText().compareTo("")==0){
			nuevo = null;
		}
		if(jtSegundoApellido.getText().compareTo("")==0){
			nuevo = null;	
		}
		else{
			nuevo = new Alumno(jtName.getText(), jtPrimerApellido.getText(), jtSegundoApellido.getText(), jtPueblo.getText(), Integer.parseInt(jtEdad.getText()), sexo);
		}
		return nuevo;
	}

	@Override
	public void itemStateChanged(ItemEvent evento) {
		if(evento.getStateChange() == ItemEvent.SELECTED){
			if((JRadioButton)evento.getSource()==rHombre){
				sexo = false;
			}
			if((JRadioButton)evento.getSource()==rMujer){
				sexo = true;
			}
		}
		
	}
}
