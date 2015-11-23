package ficheros1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		//Main.copiar();

	}

	private void copiar(String origen, String destino) {
		//String origen;
		//String destino;
		FileInputStream in = null;
		FileOutputStream out = null;
		int b;
		
		try{
			in = new FileInputStream(origen);
			out = new FileOutputStream(destino);
			
			while((b = in.read()) != -1){
				out.write(b);
			}
		}catch(FileNotFoundException e ){}
		 catch (Exception e){}
		
		finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){}
			}
			if(out != null){}
				try{
					out.close();
				}catch(Exception e){}
		}
	}
}
