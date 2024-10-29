package bin;

public class Reserva {
	private Clases clase;
	private Cliente cliente;
	
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reserva(Clases clase, Cliente usuario) {
		super();
		this.clase = clase;
		this.cliente = usuario;
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}

	public Cliente getUsuario() {
		return cliente;
	}

	public void setUsuario(Cliente usuario) {
		this.cliente = usuario;
	}

	@Override
	public String toString() {
		return "Reserva [clase=" + clase + ", usuario=" + cliente + "]";
	}
	
	
	
}
