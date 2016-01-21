package popbl3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class DialogoContrase�aNueva extends JDialog implements ActionListener{

	JPasswordField contrase�aNueva;
	JPasswordField contrase�aRepetida;
	
	String newContrase�a = null;
	
	public DialogoContrase�aNueva(JDialog panelAnterior){
		super(panelAnterior,"Nueva contrase�a");
		super.setModal(true);
		
		this.setSize(300,225);
		this.setLocation(200,50);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.add(crearPanelPasswordFields(),BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		
		return panel;
	}

	private Component crearPanelPasswordFields() {
		JPanel panel = new JPanel (new GridLayout(2,1,0,10));
		
		contrase�aNueva = new JPasswordField();
		contrase�aRepetida= new JPasswordField();
		
		panel.add(crearPasswordField(contrase�aNueva,"Contrase�a nueva"));
		panel.add(crearPasswordField(contrase�aRepetida,"Repita la contrase�a"));		
		
		return panel;
	}

	private Component crearPasswordField(JPasswordField text, String titulo) {
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

	private void comprobarContrase�as() throws HayCamposIncompletos, Contrase�asNoIguales{
		if(String.valueOf(contrase�aNueva.getPassword()).equals("")) throw new HayCamposIncompletos();
		if(String.valueOf(contrase�aRepetida.getPassword()).equals("")) throw new HayCamposIncompletos();
		if (String.valueOf(contrase�aRepetida.getPassword())
				.equals(String.valueOf(contrase�aNueva.getPassword())) == false) {
			throw new Contrase�asNoIguales();
		}
	}

	
	private class HayCamposIncompletos extends Exception{
		
		public HayCamposIncompletos(){
			super();
		}
		
	}
	private class Contrase�asNoIguales extends Exception{
		
		public Contrase�asNoIguales(){
			super();
		}
		
	}
	
	public String getNewContrase�a(){
		return newContrase�a;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ok")){
			try{
			comprobarContrase�as();
			
			newContrase�a = String.valueOf(contrase�aNueva.getPassword());
			
			this.dispose();
		
			}catch(HayCamposIncompletos error){
				JOptionPane.showMessageDialog(this,"Rellene todos los campos", "Error",JOptionPane.ERROR_MESSAGE);
			}catch(Contrase�asNoIguales error){
				JOptionPane.showMessageDialog(this,"Las contrase�as no se escribieron igual", "Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("cancel")){
			this.dispose();
		}
		
	}

	
}
