package Calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculadora extends JFrame implements ActionListener {

	JFrame ventana;
	
	JLabel lValor;
	int contador;
	double resultado;
	String signo;
	String calc1, calc2;
	JButton cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve;
	JButton plus, minus, div, multi, vacio, vacio2, igual;
	
	public Calculadora(){
		ventana = new JFrame("Calculadora");
		ventana.setSize(400, 350);
		ventana.setLocation(450, 200);
		
		ventana.setContentPane(crearPanelVentana());
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private Container crearPanelVentana(){
		JPanel panel= new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 40, 20));
		
		lValor = new JLabel();
		lValor.setFont(new Font("Verdana",Font.CENTER_BASELINE,40));
		lValor.setForeground(Color.black);
		lValor.setHorizontalAlignment(lValor.RIGHT );
		
		panel.add(lValor,BorderLayout.NORTH);
		
		panel.add(crearPanelBoton(), BorderLayout.SOUTH);
		
		return panel;
	}
	
	private Component crearPanelBoton() {
		JPanel panel = new JPanel(new GridLayout(4,4,0,0));
		
		uno= new JButton ("1");
		uno.addActionListener(this);
		uno.setActionCommand("1");
		panel.add(uno);
		
		
		dos= new JButton ("2");
		dos.addActionListener(this);
		dos.setActionCommand("2");
		panel.add(dos);
		
		tres= new JButton ("3");
		tres.addActionListener(this);
		tres.setActionCommand("3");
		panel.add(tres);
		
		plus= new JButton ("+");
		plus.addActionListener(this);
		plus.setActionCommand("+");
		panel.add(plus);
		
		cuatro= new JButton ("4");
		cuatro.addActionListener(this);
		cuatro.setActionCommand("4");
		panel.add(cuatro);
		
		cinco= new JButton ("5");
		cinco.addActionListener(this);
		cinco.setActionCommand("5");
		panel.add(cinco);
		
		seis= new JButton ("6");
		seis.addActionListener(this);
		seis.setActionCommand("6");
		panel.add(seis);
		
		minus= new JButton ("-");
		minus.addActionListener(this);
		minus.setActionCommand("-");
		panel.add(minus);
		
		
		siete= new JButton ("7");
		siete.addActionListener(this);
		siete.setActionCommand("7");
		panel.add(siete);
		
		ocho= new JButton ("8");
		ocho.addActionListener(this);
		ocho.setActionCommand("8");
		panel.add(ocho);
		
		nueve= new JButton ("9");
		nueve.addActionListener(this);
		nueve.setActionCommand("9");
		panel.add(nueve);
		
		multi= new JButton ("*");
		multi.addActionListener(this);
		multi.setActionCommand("*");
		panel.add(multi);
			
		
		vacio= new JButton ("");
		vacio.addActionListener(this);
		vacio.setActionCommand("");
		panel.add(vacio);
		
		cero= new JButton ("0");
		cero.addActionListener(this);
		cero.setActionCommand("0");
		panel.add(cero);
		

		div= new JButton ("/");
		div.addActionListener(this);
		div.setActionCommand("/");
		panel.add(div);
		
		igual= new JButton ("=");
		igual.addActionListener(this);
		igual.setActionCommand("=");
		panel.add(igual);
			
		return panel;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()){
		case "0":lValor.setText(lValor.getText()+"0"); break;
		case "1":lValor.setText(lValor.getText()+"1"); break;
		case "2": lValor.setText(lValor.getText()+"2"); break;
		case "3":lValor.setText(lValor.getText()+"3"); break;
		case "4": lValor.setText(lValor.getText()+"4"); break;
		case "5":lValor.setText(lValor.getText()+"5"); break;
		case "6": lValor.setText(lValor.getText()+"6"); break;
		case "7":lValor.setText(lValor.getText()+"7"); break;
		case "8": lValor.setText(lValor.getText()+"8"); break;
		case "9":lValor.setText(lValor.getText()+"9"); break;
		
		
		case "+": calc1 = lValor.getText();
		lValor.setText("");
				signo = "+";break;
		case "-":
			 calc1 = lValor.getText();
			 lValor.setText("");
				signo = "-";break;
		case "/":
			 calc1 = lValor.getText();
			 lValor.setText("");
				signo = "/";break;
		case "*":
			 calc1 = lValor.getText();
			 lValor.setText("");
				signo = "*";break;
		case "=":calc2 = lValor.getText();
		lValor.setText(" ");
			Ejercicios();
			lValor.setText(String.valueOf(resultado));
			break;
				
		default: lValor.setText("Error");
		}
		
	}
	
	private void Ejercicios() {
		if(signo.equals("+")){
			resultado = Double.parseDouble(calc1) + Double.parseDouble(calc2);
		}else if(signo.equals("-")){
			resultado = Double.parseDouble(calc1) - Double.parseDouble(calc2);
		}else if(signo.equals("*")){
			resultado = Double.parseDouble(calc1) * Double.parseDouble(calc2);
		}else if(signo.equals("/")){
			resultado = Double.parseDouble(calc1) / Double.parseDouble(calc2);
		}
		
	}
	
	public static void main(String[] args) {
		Calculadora calculadora= new Calculadora();
	}
}
