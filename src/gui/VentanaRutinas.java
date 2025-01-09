package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaRutinas extends JFrame {
    
	private static final long serialVersionUID = 1L;
	JTable tabla;
    
    public VentanaRutinas() {
        setTitle("Rutinas Personalizadas");
        setBounds(350, 250, 700, 330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            gestionarRutinas();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
    }
    
    public void gestionarRutinas() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:Gym.db");

            DefaultTableModel modelo = new DefaultTableModel();
            tabla = new JTable(modelo);

            modelo.addColumn("Lunes");
            modelo.addColumn("Martes");
            modelo.addColumn("Miercoles");
            modelo.addColumn("Jueves");
            modelo.addColumn("Viernes");
            modelo.addColumn("Nº Rutina");
            
            cargarDatos(conexion, modelo);
            
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
    
    private static void cargarDatos(Connection conexion, DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);

            String sql = "SELECT * FROM Rutinas";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("Lunes"),
                        rs.getString("Martes"),
                        rs.getString("Miercoles"),
                        rs.getString("Jueves"),
                        rs.getString("Viernes"),
                        rs.getInt("Id")
                });
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
