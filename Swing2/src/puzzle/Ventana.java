package puzzle;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame implements ActionListener{

	final int FILAS = 4;
	final int COLUMNAS = 3;
	int holeX, holeY;
	int piezas[][] = {{1,2,3},{4,5,6},{7,8,9},{10,11,0}};
	JPanel panel; 
	
	public Ventana(int holeX, int holeY, int[][] pieces, JPanel panel) throws HeadlessException {
		super();
		this.holeX = holeX;
		this.holeY = holeY;
		this.piezas = pieces;
		this.panel = panel;
	}
	
	public Ventana(){
		super ("Puzzle");
		this.setLocation(100,100);
		this.setSize(420,300);
		piezas = new int [FILAS] [COLUMNAS];
		initializePieces();
		
		this.setContentPane(createWindowPanel());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	private void initializePieces() {
		Random generator = new Random();
		for (int i = 0; i < FILAS; i++){
			for (int j = 0; j < COLUMNAS; ){
				int piece = generator.nextInt(11)+1; 
				if (!included(piece)){
					piezas[i][j]= piece;
					j++;
				}
				if (i==FILAS-1&& j==COLUMNAS-1){ 
					piezas[i][j]=0;
					holeX = i;
					holeY = j;
					return;
				}
			}
		}
	}
	private boolean included(int piece) {
		for (int i = 0; i < FILAS; i++){
			for (int j = 0; j < COLUMNAS; j++){
				if (piezas[i][j]== piece){
					return true;
				}
			}
		}
		return false;
	}
	private Container createWindowPanel() {
		panel = new JPanel (new GridLayout (FILAS,COLUMNAS,5,5));
		JButton button;
		for (int i = 0; i < FILAS; i++){
			for (int j = 0; j < COLUMNAS; j++){ 
				String name = "tigre"+piezas[i][j]+".png";
				ImageIcon imagen = new ImageIcon ("imagen/" + name);
				button = new JButton(imagen);
				button.addActionListener(this);
				button.setActionCommand(i+" "+j);
				panel.add(button);
			}
		}
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int mov;
		String comando = e.getActionCommand();
		int i = Integer.valueOf(comando.substring(0, comando.indexOf(' '))).intValue();
		int j = Integer.valueOf(comando.substring(comando.indexOf(' ')+1)).intValue();
		if ((mov = valid(i,j))!=0){
			JButton boton =(JButton)e.getSource(); 
			Icon imagen = boton.getIcon();
			
			((JButton)panel.getComponent(holeX * COLUMNAS + holeY)).setIcon(imagen);
			boton.setIcon(null);
			holeX= i;
			holeY = j;
		}else{
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
			Toolkit.getDefaultToolkit().beep();
		}
		
	}
	private int valid(int i, int j) {
		
		if (((i-1)==holeX)&&(j == holeY)) return 1;
		
		if ((i== holeX)&&((j-1)==holeY)) return 2;
		
		if (((i+1)==holeX) && (j==holeY))  return 3;
		
		if ((i== holeX) && ((j+1)== holeY)) return 4;
		
		return 0;
	}


}