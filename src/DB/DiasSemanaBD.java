package DB;

import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DiasSemanaBD extends JFrame {

	JTable tabla;
	private static final long serialVersionUID = 1L;
	
    public DiasSemanaBD(String DiaSemana) {
        
    	String Day = DiaSemana;
    	
    	setTitle(DiaSemana);
        setBounds(350, 250, 800, 330);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            gestionarDia(Day);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gestionarDia(String DiaSemana) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:Gym.db");

            DefaultTableModel modelo = new DefaultTableModel();
            tabla = new JTable(modelo);

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

            
            tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                
                private static final long serialVersionUID = 1L;

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    // Usa la implementación de la clase base para obtener el componente
                    JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Restaurar el color de fondo predeterminado
                    if (isSelected) {
                        cell.setBackground(table.getSelectionBackground());
                    } else {
                        cell.setBackground(table.getBackground());
                    }

                    // Configurar el color de fondo según el valor de la celda
                    if ("Descanso".equals(value)) {
                        cell.setBackground(Color.GREEN);
                    } else if ("Pilates".equals(value)) {
                        cell.setBackground(Color.BLUE);
                    } else if ("Yoga".equals(value)) {
                        cell.setBackground(Color.DARK_GRAY);
                    } else if ("Spinning".equals(value)) {
                        cell.setBackground(Color.BLUE);
                    } else if ("Body Pump".equals(value)) {
                        cell.setBackground(Color.CYAN);
                    } else if ("HIIT".equals(value)) {
                        cell.setBackground(Color.GRAY);
                    } else if ("Power Yoga".equals(value)) {
                        cell.setBackground(Color.LIGHT_GRAY);
                    } else if ("Cardio".equals(value)) {
                        cell.setBackground(Color.MAGENTA);
                    } else if ("TRX".equals(value)) {
                        cell.setBackground(Color.ORANGE);
                    } else if ("Zumba".equals(value)) {
                        cell.setBackground(Color.PINK);
                    } else if ("Boxeo".equals(value)) {
                        cell.setBackground(Color.RED);
                    } else if ("Crossfit".equals(value)) {
                        cell.setBackground(Color.WHITE);
                    } else if ("Stretching".equals(value)) {
                        cell.setBackground(Color.YELLOW);
	                } else if ("Body Combat".equals(value)) {
	                    cell.setBackground(Color.BLACK);
	                    
	                }

                    return cell;
                }
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

}

