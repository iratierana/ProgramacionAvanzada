
public class ResultadoAprendizaje {

	String texto;
	float nota;
	
	public ResultadoAprendizaje (String texto, float nota){	
		this.texto = texto;
		this.nota = nota;
	}
	
	@Override
	public String toString() {		
		return this.texto+":   "+this.nota;
	}
	
	public float getNota() {
		return nota;
	}

	public String getTexto() {
		return texto;
	}
}
