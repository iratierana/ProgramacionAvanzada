package segundero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Segundero implements ActionListener{

	JFrame ventana;
	JLabel lValor;
	JButton bStart, bStop;
	int contador;
	Timer timer= null;
	MiTarea miTarea;
	boolean fin= false;
	
	public Segundero (){
		ventana = new JFrame ("Segundero");
		miTarea = new MiTarea();
		ventana.setSize (300,400);
		ventana.setLocation (100,100);
		ventana.setContentPane (crearPanelVentana());
		ventana.setVisible (true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		
		lValor = new JLabel (String.valueOf(contador));
		lValor.setFont ( new Font("arial",Font.BOLD,67));
		lValor.setForeground(Color.red);
		lValor.setHorizontalAlignment(JLabel.CENTER);
		
		panel.add (lValor, BorderLayout.CENTER);
		panel.add (crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel (new GridLayout(1,2,20,0));
		panel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		
		bStart = new JButton ("Start");
		bStart.addActionListener(this);
		bStart.setActionCommand("start");
		
		bStop = new JButton ("Stop");
		bStop.addActionListener(this);
		bStop.setActionCommand("stop");
		
		panel.add(bStart);
		panel.add(bStop);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==bStart){
			if (timer == null){
				timer = new Timer();
				miTarea = new MiTarea();
				timer.schedule(miTarea, 0, 1000);
			}else{
				JOptionPane.showMessageDialog(ventana, "El cronometro ya esta activo",
						"Error Activar",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		if (e.getActionCommand().equals("stop")){
			if (timer!=null){
				timer.cancel();
				timer.purge();
				
				timer = null;
				contador= 0;
			}else{
				JOptionPane.showMessageDialog(ventana, "El cronometro no esta activo",
						"Error Parar",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	public class MiTarea extends TimerTask{
		public void run (){
	
		//	SwingUtilities.invokeLater(new Runnable(){
		//		public void run(){
					lValor.setText(String.valueOf(++contador));
				//}	
		//	});
			
		}
	}
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		Segundero ejercicio = new Segundero();
	}


}
