package gui;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MonitorBd extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MonitorBd() {
        setTitle("Gestión de Monitores");
        setBounds(10, 10, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            gestionarMonitores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gestionarMonitores() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:Gym.db");

            DefaultTableModel modelo = new DefaultTableModel();
            JTable tabla = new JTable(modelo);

            modelo.addColumn("Autorización");
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Email");
            modelo.addColumn("Edad");
            modelo.addColumn("Especialidad");
            modelo.addColumn("Teléfono");

            cargarDatos(conexion, modelo);

            JButton btnInsertar = new JButton("Insertar");
            JButton btnModificar = new JButton("Modificar");
            JButton btnEliminar = new JButton("Eliminar");
            JButton btnCerrar = new JButton("Cerrar");

            JPanel panelBotones = new JPanel();
            panelBotones.add(btnInsertar);
            panelBotones.add(btnModificar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnCerrar);

            getContentPane().add(new JScrollPane(tabla), "Center");
            getContentPane().add(panelBotones, "South");
            
            // Acción para el botón Insertar
            btnInsertar.addActionListener(e -> {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField txtAutorizacion = new JTextField();
                JTextField txtNombre = new JTextField();
                JTextField txtApellido = new JTextField();
                JTextField txtEmail = new JTextField();
                JTextField txtEdad = new JTextField();
                JTextField txtEspecialidad = new JTextField();
                JTextField txtTelefono = new JTextField();

                panel.add(new JLabel("Autorización:"));
                panel.add(txtAutorizacion);
                panel.add(new JLabel("Nombre:"));
                panel.add(txtNombre);
                panel.add(new JLabel("Apellido:"));
                panel.add(txtApellido);
                panel.add(new JLabel("Email:"));
                panel.add(txtEmail);
                panel.add(new JLabel("Edad:"));
                panel.add(txtEdad);
                panel.add(new JLabel("Especialidad:"));
                panel.add(txtEspecialidad);
                panel.add(new JLabel("Teléfono:"));
                panel.add(txtTelefono);

                int resultado = JOptionPane.showConfirmDialog(null, panel, "Insertar Monitor", JOptionPane.OK_CANCEL_OPTION);
                if (resultado == JOptionPane.OK_OPTION) {
                    try {
                        String sql = "INSERT INTO monitor (autorizacion, nombre, apellido, email, edad, especialidad, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conexion.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt(txtAutorizacion.getText()));
                        ps.setString(2, txtNombre.getText());
                        ps.setString(3, txtApellido.getText());
                        ps.setString(4, txtEmail.getText());
                        ps.setInt(5, Integer.parseInt(txtEdad.getText()));
                        ps.setString(6, txtEspecialidad.getText());
                        ps.setInt(7, Integer.parseInt(txtTelefono.getText()));
                        ps.executeUpdate();
                        
                        modelo.addRow(new Object[]{
                            txtAutorizacion.getText(), txtNombre.getText(), txtApellido.getText(),
                            txtEmail.getText(), txtEdad.getText(), txtEspecialidad.getText(), txtTelefono.getText()
                        });
                        JOptionPane.showMessageDialog(null, "Monitor insertado correctamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al insertar: " + ex.getMessage());
                    }
                }
            });

            // Acción para el botón Modificar
            btnModificar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JTextField txtAutorizacion = new JTextField(modelo.getValueAt(fila, 0).toString());
                    JTextField txtNombre = new JTextField(modelo.getValueAt(fila, 1).toString());
                    JTextField txtApellido = new JTextField(modelo.getValueAt(fila, 2).toString());
                    JTextField txtEmail = new JTextField(modelo.getValueAt(fila, 3).toString());
                    JTextField txtEdad = new JTextField(modelo.getValueAt(fila, 4).toString());
                    JTextField txtEspecialidad = new JTextField(modelo.getValueAt(fila, 5).toString());
                    JTextField txtTelefono = new JTextField(modelo.getValueAt(fila, 6).toString());

                    panel.add(new JLabel("Autorización:"));
                    panel.add(txtAutorizacion);
                    panel.add(new JLabel("Nombre:"));
                    panel.add(txtNombre);
                    panel.add(new JLabel("Apellido:"));
                    panel.add(txtApellido);
                    panel.add(new JLabel("Email:"));
                    panel.add(txtEmail);
                    panel.add(new JLabel("Edad:"));
                    panel.add(txtEdad);
                    panel.add(new JLabel("Especialidad:"));
                    panel.add(txtEspecialidad);
                    panel.add(new JLabel("Teléfono:"));
                    panel.add(txtTelefono);

                    int resultado = JOptionPane.showConfirmDialog(null, panel, "Modificar Monitor", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado == JOptionPane.OK_OPTION) {
                        try {
                            String sql = "UPDATE monitor SET autorizacion = ?, nombre = ?, apellido = ?, email = ?, edad = ?, especialidad = ?, telefono = ? WHERE telefono = ?";
                            PreparedStatement ps = conexion.prepareStatement(sql);
                            ps.setInt(1, Integer.parseInt(txtAutorizacion.getText()));
                            ps.setString(2, txtNombre.getText());
                            ps.setString(3, txtApellido.getText());
                            ps.setString(4, txtEmail.getText());
                            ps.setInt(5, Integer.parseInt(txtEdad.getText()));
                            ps.setString(6, txtEspecialidad.getText());
                            ps.setInt(7, Integer.parseInt(txtTelefono.getText()));
                            ps.setInt(8, Integer.parseInt(modelo.getValueAt(fila, 6).toString()));

                            ps.executeUpdate();

                            modelo.setValueAt(txtAutorizacion.getText(), fila, 0);
                            modelo.setValueAt(txtNombre.getText(), fila, 1);
                            modelo.setValueAt(txtApellido.getText(), fila, 2);
                            modelo.setValueAt(txtEmail.getText(), fila, 3);
                            modelo.setValueAt(txtEdad.getText(), fila, 4);
                            modelo.setValueAt(txtEspecialidad.getText(), fila, 5);
                            modelo.setValueAt(txtTelefono.getText(), fila, 6);

                            JOptionPane.showMessageDialog(null, "Monitor modificado correctamente.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un monitor para modificar.");
                }
            });

            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    int telefono = Integer.parseInt(modelo.getValueAt(fila, 6).toString());
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este monitor?");
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "DELETE FROM monitor WHERE telefono = ?";
                            PreparedStatement ps = conexion.prepareStatement(sql);
                            ps.setInt(1, telefono);
                            ps.executeUpdate();
                            
                            modelo.removeRow(fila);
                            JOptionPane.showMessageDialog(null, "Monitor eliminado correctamente.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un monitor para eliminar.");
                }
            });
            
            btnCerrar.addActionListener(e -> {
            	dispose();
            });
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static void cargarDatos(Connection conexion, DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);

            String sql = "SELECT * FROM monitor";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("autorizacion"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getInt("edad"),
                        rs.getString("especialidad"),
                        rs.getInt("telefono")
                });
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
