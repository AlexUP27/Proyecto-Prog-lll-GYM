package gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import bin.Monitor;

public class MonitorTabla extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Monitor> monitor;
	private String[] columnas = { "Autorizacion" , "Nombre" , "Apellido", "Email", "Edad", "Especialidad", "Telefono" };
	
	public MonitorTabla(List<Monitor> monitor, String[] columnas) {
		this.monitor = monitor;
	}
	
	@Override
	public int getRowCount() {
		return monitor.size();
	}
	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Monitor c = monitor.get(rowIndex);
		switch (columnIndex) {
			case 0: return c.getAutorizacion();
			case 1: return c.getNombre();
			case 2: return c.getApellido();
			case 3: return c.getEmail();
			case 4: return c.getEdad();
			case 5: return c.getEspecialidad();
			case 6: return c.getTelefono();
		}
		return null;
	}
	

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Monitor c = monitor.get(columnIndex);
		switch (columnIndex) {
			case 0: c.setAutorizacion((int) aValue);
					break;
			case 1: c.setNombre((String) aValue);
					break;
			case 2: c.setApellido((String) aValue);
					break;
			case 3: c.setEmail( (String) aValue);
					break;
			case 4: c.setEdad((int) aValue);
					break;
			case 5: c.setEspecialidad((String) aValue);
					break;
			case 6: c.setTelefono((int) aValue);
					break;
		}
	}
	
	
	@Override
	public String getColumnName(int column) {
		return columnas[column];
		
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
}
