package popbl3;

public class Fisio extends Usuario {
	private final String SEPARADOR = "$";
	public Fisio(String userName,String nombre, String apellido1, String apellido2,String centro) {
		super(userName, nombre, apellido1, apellido2,centro);
		// TODO Auto-generated constructor stub
	}
	public Fisio(String userName) {
		super(userName);
		// TODO Auto-generated constructor stub
	}
	
	public String guardar() {		
		return super.guardar();
	}
	@Override
	public String toString() {
		
		return this.getNombre()+" "+this.getApellido1()+" "+this.getApellido2();
	}
	
	public void setFisio(Fisio fisio) {
		super.setUserName(fisio.getUserName());
		super.setNombre(fisio.getNombre());
		super.setApellido1(fisio.getApellido1());
		super.setApellido2(fisio.getApellido2());
		super.setCentro(fisio.getCentro());
	}

}
