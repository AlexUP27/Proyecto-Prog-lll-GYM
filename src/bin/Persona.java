package bin;

public class Persona {
	private String nomPersona;
	private String telefono;
	private String email;
	private String dir;
	
	public Persona() {
		super();
	}

	public Persona(String nomPersona, String telefono, String email, String dir) {
		super();
		this.nomPersona = nomPersona;
		this.telefono = telefono;
		this.email = email;
		this.dir = dir;
	}

	public String getNomPersona() {
		return nomPersona;
	}

	public void setNomPersona(String nomPersona) {
		this.nomPersona = nomPersona;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	@Override
	public String toString() {
		return "Persona [nomPersona=" + nomPersona + ", telefono=" + telefono + ", email=" + email + ", dir=" + dir
				+ "]";
	}
	
	
}
