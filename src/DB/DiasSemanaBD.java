package DB;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DiasSemanaBD extends JFrame implements TableCellRenderer{

	private static final long serialVersionUID = 1L;
	
    public DiasSemanaBD(String DiaSemana) {
        
    	String Day = DiaSemana;
    	
    	setTitle("Gestión de Clientes");
        setBounds(350, 250, 800, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            gestionarClientes(Day);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gestionarClientes(String DiaSemana) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:Gym.db");

            DefaultTableModel modelo = new DefaultTableModel();
            JTable tabla = new JTable(modelo);

            modelo.addColumn("Hora");
            modelo.addColumn("Clase1");
            modelo.addColumn("Clase2");
            modelo.addColumn("Clase3");
            modelo.addColumn("Clase4");
            modelo.addColumn("Clase5");
            
            cargarDatos(conexion, modelo, DiaSemana);
            
            JButton btnCerrar = new JButton("Cerrar");

            JPanel panelBotones = new JPanel();
            
            panelBotones.add(btnCerrar);

            getContentPane().add(new JScrollPane(tabla), "Center");
            getContentPane().add(panelBotones, "South");
            
            btnCerrar.addActionListener(e -> {
            	dispose();
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos(Connection conexion, DefaultTableModel modelo, String DiaSemana) {
        try {
            modelo.setRowCount(0);

            String sql = "SELECT * FROM " + DiaSemana + "Turnos";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("Hora"),
                        rs.getString("Clase1"),
                        rs.getString("Clase2"),
                        rs.getString("Clase3"),
                        rs.getString("Clase4"),
                        rs.getString("Clase5")
                });
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = table.getDefaultRenderer(table.getColumnClass(column)).getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, column);

	    // Verificar si el valor de la celda es "Descanso"
	    if ("Descanso".equals(value)) {
	        c.setBackground(Color.GREEN); // Poner el color de fondo verde
	    } else {
	        c.setBackground(table.getBackground()); // Mantener el color de fondo predeterminado
	    }
	
	    return c;
	}

}

