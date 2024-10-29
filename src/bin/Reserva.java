package bin;

public class Reserva {
	private Clases clase;
	private Usuario usuario;
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(Clases clase, Usuario usuario) {
		super();
		this.clase = clase;
		this.usuario = usuario;
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Reserva [clase=" + clase + ", usuario=" + usuario + "]";
	}
	
	
	
}
