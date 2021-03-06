package ariketa1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MiDialogoAlumnos extends JDialog implements ActionListener{

	JPanel panelPrincipal, panelSur, panelCentro;
	JLabel lNombre, lApellido1, lApellido2;
	JTextField tfNombre, tfApellido1, tfApellido2;
	JButton bOk, bCancelar;
	
	public MiDialogoAlumnos(JFrame ventana) {
		super(ventana, "crearalumo");
		lNombre = new JLabel("Nombre");
		lApellido1 = new JLabel("Primer apellido");
		lApellido2 = new JLabel("Segundo apellido");
		
		tfNombre = new JTextField("");
		tfApellido1 = new JTextField("");
		tfApellido2 = new JTextField("");
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelCentro = new JPanel(new GridLayout(5, 2, 20, 20));
		panelSur = new JPanel(new GridLayout(1, 2, 20, 0));
		
		bOk = new JButton("Aceptar");
		bCancelar = new JButton("Cancelar");

		this.setSize(400, 350);
		this.setLocation(450, 230);
		agregarListeners();
		rellenarPanel();
		this.setModal(true);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	private void rellenarPanel() {
		panelCentro.add(lNombre);
		panelCentro.add(tfNombre);
		panelCentro.add(lApellido1);
		panelCentro.add(tfApellido1);
		panelCentro.add(lApellido2);
		panelCentro.add(tfApellido2);
		panelCentro.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Datos Alumno"),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		panelPrincipal.add(panelCentro, BorderLayout.CENTER);
		
		panelSur.add(bOk);
		panelSur.add(bCancelar);
		panelSur.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
		panelPrincipal.add(panelSur, BorderLayout.SOUTH);
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setContentPane(panelPrincipal);
	}

	private void agregarListeners() {
		bOk.addActionListener(this);
		bCancelar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		
		case "Aceptar":
			if(comprobarDatos())
				this.dispose();
			else
				JOptionPane.showMessageDialog(this,"Rellenar todos los campos","ERROR",JOptionPane.ERROR_MESSAGE);
			break;
		case "Cancelar":
			tfNombre.setText("");
			tfApellido1.setText("");
			tfApellido2.setText("");
			this.dispose();
			break;

		default:
			break;
		}
	}
	
	private boolean comprobarDatos() {
		if(0!=tfNombre.getText().compareTo("")){
			if(0!=tfApellido1.getText().compareTo("")){
				if(0!=tfApellido2.getText().compareTo("")){
					return true;
						}else
							return true;

				
				}
		}
	
	return false;
	}
	public Alumno getAlumno(){
		String nombre = null;
		String apellido1 = null;
		String apellido2 = null;
		Alumno alumno = new Alumno(nombre, apellido1, apellido2);
		if(tfNombre.getText().compareTo("")==0){
			alumno = null;
		}else if(0==tfApellido2.getText().compareTo("")){
			alumno = new Alumno(tfApellido1.getText(),
					tfApellido2.getText(),
					tfNombre.getText());		
		}
		return alumno;
	}
	
}
