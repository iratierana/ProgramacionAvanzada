package pelotas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame implements ActionListener {
	JFrame ventana;
	ArrayList<Bola> listaBolas;
	MiPanelPintura panelPintura;
	
	public MainFrame() {
		listaBolas = new ArrayList<Bola>();
		ventana = new JFrame("Dale a 'mas..' para mas bolas");
		
		ventana.setSize(800, 500);
		ventana.setLocation(250, 100);
		
		ventana.setContentPane(crearPanelPrincipal());
		
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
	}

	private Container crearPanelPrincipal() {
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelPrincipal.add(crearPanelCentral(), BorderLayout.CENTER);
		panelPrincipal.add(crearBotonSur(), BorderLayout.SOUTH);

		return panelPrincipal;
	}

	private Component crearBotonSur() {
		JPanel panel = new JPanel(new FlowLayout());
		
		JButton botonPelotas = new JButton("Mas...");
		botonPelotas.addActionListener(this);
		botonPelotas.setActionCommand("masBolas");
		panel.add(botonPelotas);

		return panel;
	}

	private Component crearPanelCentral() {
		panelPintura = new MiPanelPintura();

		return panelPintura;
	}

	private class MiPanelPintura extends JPanel {
		@Override
		public void paint(Graphics g) {
			Bola e;
			super.paint(g);
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			for (int i = 0; i < listaBolas.size(); i++) {
				e = listaBolas.get(i);
				e.paint(g);
			}
		}
	}

	private void dibujar() {
		ventana.repaint();
	}

	private void fisicas() {
		Bola b;
	
		int dt = 1;
		for (int i = 0; i < listaBolas.size(); i++) {			
			b = listaBolas.get(i);
			   b.x += b.vx * dt;
			   b.y += b.vy * dt;
		        if (b.vx < 0 && b.x <= 0 || b.vx > 0 && b.x + 20 >= panelPintura.getWidth())
		        	b.vx = -b.vx;
		        if (b.vy < 0 && b.y < 0 || b.vy > 0 && b.y + 20 >= panelPintura.getHeight())
		        	b.vy = -b.vy;
		}
	}

	public static void main(String[] args) {
		MainFrame mainF = new MainFrame();
		
		while (true) {
			try {
				Thread.sleep(10);
				mainF.fisicas();
				mainF.dibujar();

			} catch (InterruptedException e) {

			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().toLowerCase().equals("masbolas")) {
			listaBolas.add(new Bola(10, 10, 20));
		}
	}
}
