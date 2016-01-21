import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Principal {
	
	public void copiarBytes(){
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream("Origen/nazi.png");
			out = new FileOutputStream("Destino/nazi.png");
			int b;
			
			while((b = in.read()) != -1){
				out.write(b);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {	}
			try {
				out.close();
			} catch (Exception e) {	}
		}
		
	}
	
	public void copiarChar(){
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			
			in = new BufferedReader(new FileReader("Origen/texto"));
			out = new PrintWriter(new FileWriter("Destino/texto"));
			String linea;
			while((linea = in.readLine()) != null){
				out.println(linea);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {	}
			try {
				out.close();
			} catch (Exception e) {	}
		}
		
	}
	
	
	
	

	public static void main(String[] args) {
		Principal a = new Principal();
		a.copiarBytes();
		a.copiarChar();
	}

}
