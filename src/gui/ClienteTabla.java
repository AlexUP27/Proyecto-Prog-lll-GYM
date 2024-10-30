package gui;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import bin.Cliente;
import bin.Cliente.Genre;

public class ClienteTabla extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Cliente> clientes;
	private String[] columnas = { "Nombre" , "Apellido" , "Telefono", "Email", "Dierccion", "Edad", "Genero" };
	
	public ClienteTabla(List<Cliente> personas, String[] columnas) {
		this.clientes = personas;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente c = clientes.get(rowIndex);
		switch (columnIndex) {
			case 0: return c.getNombre();
			case 1: return c.getApellido();
			case 2: return c.getTelefono();
			case 3: return c.getEmail();
			case 4: return c.getDir();
			case 5: return c.getEdad();
			case 6: return c.getGenre();
		}
		return null;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente c = clientes.get(columnIndex);
		switch (columnIndex) {
			case 0: c.setNombre((String) aValue);
					break;
			case 1: c.setApellido((String) aValue);
					break;
			case 2: c.setTelefono((int) aValue);
					break;
			case 3: c.setEmail( (String) aValue);
					break;
			case 4: c.setDir((String) aValue);
					break;
			case 5: c.setEdad((int) aValue);
					break;
			case 6: c.setGenre((Genre) aValue);
					break;
		}
	}
	
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
}
