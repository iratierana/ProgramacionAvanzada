package panelDePremios;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener{
	
	JFrame ventana;
	JButton bSalir, botones[][];
	JLabel lValor;
	JTextField puntuacion;
	final int MAXCOLUMNAS = 6;
	final int MAXFILAS = 6;
	int contador;
	final int MAXCEROS = 8;

	public Main(){
		ventana = new JFrame("Panel de premios");
		ventana.setSize(600,600);
		ventana.setLocation(100,100);
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		
		panel.add(crearPanelTexto(),BorderLayout.NORTH);
		panel.add(CrearPanelBotones(),BorderLayout.CENTER);
		panel.add(crearPanelBotonSalir(),BorderLayout.SOUTH);
		
		
		return panel;
	}

	private Component crearPanelTexto() {
		JPanel panel = new JPanel (new FlowLayout());
		puntuacion = new JTextField();
		puntuacion.setPreferredSize(new Dimension(150,30));
		JLabel texto = new JLabel("Premio acumulado:");
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panel.add(texto);
		panel.add(puntuacion);
		
		return panel;
	}

	private Component CrearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(MAXFILAS,MAXCOLUMNAS));
		panel.setBorder(BorderFactory.createEmptyBorder(10,0, 10, 0));
		
		botones = new JButton[MAXFILAS][MAXCOLUMNAS];
		for(int i = 0; i <MAXFILAS ; i++){
			for(int j = 0 ; j < MAXCOLUMNAS ; j++){
				botones[i][j] = new JButton();
				botones[i][j].addActionListener(this);
				botones[i][j].setActionCommand(generarNumerosAleat());
				panel.add(botones[i][j]);
			}
		}
		colocarCeros();
		return panel;
	}
	

	private Component crearPanelBotonSalir() {
		JPanel panel = new JPanel (new GridLayout(1,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,250,0,250));
		
		bSalir = new JButton("Salir");
		bSalir.addActionListener(this);
		
		panel.add(bSalir);
		
		return panel;
	}

	public String generarNumerosAleat(){
		Random generador = new Random();
		return String.valueOf(generador.nextInt(35000)+1);
		
	}
	
	public void colocarCeros(){
		Random generador = new Random();
		int posX;
		int posY;
		for(int i = 0; i<= MAXCEROS;){
			try{
				posX = generador.nextInt(MAXFILAS);
				posY = generador.nextInt(MAXCOLUMNAS);
				if(botones[posX][posY].getText() == "0"){
					throw new CasillaOcupadaException("Casilla ocupada con un 0");
				}else{
					botones[posX][posY].setActionCommand("0");
				}
				i++;
			}catch(CasillaOcupadaException e){
				
			}
		}
	}
	
	public static void main(String[] args) {
		try {
		} catch (Exception e) {

			e.printStackTrace();
		} 

		Main ejercicio = new Main();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "0"){
			JOptionPane.showMessageDialog(ventana, "La cagaste","Castigo",JOptionPane.ERROR_MESSAGE);
			ventana.setVisible(false);
			ventana.dispose();

		}
		if(e.getSource() == bSalir ){
			ventana.setVisible(false);
			ventana.dispose();
		}
		if(e.getActionCommand() != "0"){
			contador = contador + Integer.valueOf(e.getActionCommand());
			puntuacion.setText(String.valueOf(contador));
			for(int i = 0; i< MAXFILAS ; i++){
				for(int j = 0; j<MAXCOLUMNAS ; j++){
					if(e.getSource() == botones[i][j]){
						botones[i][j].setText(e.getActionCommand());
					}
				}
			}
		}
		
	}

}
