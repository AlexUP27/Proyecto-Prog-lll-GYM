package bin;

public class Clases {
	private String nombre;
	private TipoClases tipo;
	private int capcidadmax;
	private Monitor monitor;

	public Clases() {
		super();
	}

	public Clases(String nombre, TipoClases tipo, int capcidadmax, Monitor monitor) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.capcidadmax = capcidadmax;
		this.monitor = monitor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoClases getTipo() {
		return tipo;
	}

	public void setTipo(TipoClases tipo) {
		this.tipo = tipo;
	}

	public int getCapcidadmax() {
		return capcidadmax;
	}

	public void setCapcidadmax(int capcidadmax) {
		this.capcidadmax = capcidadmax;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	@Override
	public String toString() {
		return "Clases [nombre=" + nombre + ", tipo=" + tipo + ", capcidadmax=" + capcidadmax + ", monitor=" + monitor
				+ "]";
	}
	
	
	
}
