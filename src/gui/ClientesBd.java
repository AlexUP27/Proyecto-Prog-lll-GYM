package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientesBd extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Constructor que configura la ventana principal
    public ClientesBd() {
        // Configurar la ventana JFrame
        setTitle("Gestión de Clientes");
        setBounds(10, 10, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        try {
            gestionarClientes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gestionarClientes() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            String dburl = "jdbc:sqlite:Gym.db";
            Connection conexion = DriverManager.getConnection(dburl);

            DefaultTableModel modelo = new DefaultTableModel();
            JTable tabla = new JTable(modelo);

            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Email");
            modelo.addColumn("Dirección");
            modelo.addColumn("Edad");

            cargarDatos(conexion, modelo);

            JButton btnInsertar = new JButton("Insertar");
            JButton btnModificar = new JButton("Modificar");
            JButton btnEliminar = new JButton("Eliminar");

            JPanel panelBotones = new JPanel();
            panelBotones.add(btnInsertar);
            panelBotones.add(btnModificar);
            panelBotones.add(btnEliminar);

            // Agregar la tabla y los botones al marco (JFrame)
            getContentPane().add(new JScrollPane(tabla), "Center");
            getContentPane().add(panelBotones, "South");

            // Listener para insertar
            btnInsertar.addActionListener(e -> {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                JTextField txtNombre = new JTextField();
                JTextField txtApellido = new JTextField();
                JTextField txtTelefono = new JTextField();
                JTextField txtEmail = new JTextField();
                JTextField txtDireccion = new JTextField();
                JTextField txtEdad = new JTextField();

                panel.add(new JLabel("Nombre:"));
                panel.add(txtNombre);
                panel.add(new JLabel("Apellido:"));
                panel.add(txtApellido);
                panel.add(new JLabel("Teléfono:"));
                panel.add(txtTelefono);
                panel.add(new JLabel("Email:"));
                panel.add(txtEmail);
                panel.add(new JLabel("Dirección:"));
                panel.add(txtDireccion);
                panel.add(new JLabel("Edad:"));
                panel.add(txtEdad);

                int resultado = JOptionPane.showConfirmDialog(null, panel, "Insertar Cliente", JOptionPane.OK_CANCEL_OPTION);
                if (resultado == JOptionPane.OK_OPTION) {
                    try {
                        String sql = "INSERT INTO cliente (nombre, apellido, telefono, email, dir, edad) VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement ps = conexion.prepareStatement(sql);
                        ps.setString(1, txtNombre.getText());
                        ps.setString(2, txtApellido.getText());
                        ps.setInt(3, Integer.parseInt(txtTelefono.getText()));
                        ps.setString(4, txtEmail.getText());
                        ps.setString(5, txtDireccion.getText());
                        ps.setInt(6, Integer.parseInt(txtEdad.getText()));
                        ps.executeUpdate();
                        modelo.addRow(new Object[]{txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(),
                                txtEmail.getText(), txtDireccion.getText(), txtEdad.getText()});
                        JOptionPane.showMessageDialog(null, "Cliente insertado correctamente.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al insertar: " + ex.getMessage());
                    }
                }
            });

            // Listener para modificar
            btnModificar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                    JTextField txtNombre = new JTextField(modelo.getValueAt(fila, 0).toString());
                    JTextField txtApellido = new JTextField(modelo.getValueAt(fila, 1).toString());
                    JTextField txtTelefono = new JTextField(modelo.getValueAt(fila, 2).toString());
                    JTextField txtEmail = new JTextField(modelo.getValueAt(fila, 3).toString());
                    JTextField txtDireccion = new JTextField(modelo.getValueAt(fila, 4).toString());
                    JTextField txtEdad = new JTextField(modelo.getValueAt(fila, 5).toString());

                    panel.add(new JLabel("Nombre:"));
                    panel.add(txtNombre);
                    panel.add(new JLabel("Apellido:"));
                    panel.add(txtApellido);
                    panel.add(new JLabel("Teléfono:"));
                    panel.add(txtTelefono);
                    panel.add(new JLabel("Email:"));
                    panel.add(txtEmail);
                    panel.add(new JLabel("Dirección:"));
                    panel.add(txtDireccion);
                    panel.add(new JLabel("Edad:"));
                    panel.add(txtEdad);

                    int resultado = JOptionPane.showConfirmDialog(null, panel, "Modificar Cliente", JOptionPane.OK_CANCEL_OPTION);
                    if (resultado == JOptionPane.OK_OPTION) {
                        try {
                            String sql = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, dir = ?, edad = ?, telefono = ? WHERE telefono = ?";
                            PreparedStatement ps = conexion.prepareStatement(sql);
                            ps.setString(1, txtNombre.getText());
                            ps.setString(2, txtApellido.getText());
                            ps.setString(3, txtEmail.getText());
                            ps.setString(4, txtDireccion.getText());
                            ps.setInt(5, Integer.parseInt(txtEdad.getText()));
                            ps.setInt(6, Integer.parseInt(txtTelefono.getText()));
                            ps.setInt(7, Integer.parseInt(modelo.getValueAt(fila, 2).toString()));

                            ps.executeUpdate();

                            modelo.setValueAt(txtNombre.getText(), fila, 0);
                            modelo.setValueAt(txtApellido.getText(), fila, 1);
                            modelo.setValueAt(txtTelefono.getText(), fila, 2);
                            modelo.setValueAt(txtEmail.getText(), fila, 3);
                            modelo.setValueAt(txtDireccion.getText(), fila, 4);
                            modelo.setValueAt(txtEdad.getText(), fila, 5);

                            JOptionPane.showMessageDialog(null, "Cliente modificado correctamente.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al modificar: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar.");
                }
            });

            // Listener para eliminar
            btnEliminar.addActionListener(e -> {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    int telefono = Integer.parseInt(modelo.getValueAt(fila, 2).toString());
                    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este cliente?");
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        try {
                            String sql = "DELETE FROM cliente WHERE telefono = ?";
                            PreparedStatement ps = conexion.prepareStatement(sql);
                            ps.setInt(1, telefono);
                            ps.executeUpdate();
                            modelo.removeRow(fila);
                            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un cliente para eliminar.");
                }
            });

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void cargarDatos(Connection conexion, DefaultTableModel modelo) {
        try {
            modelo.setRowCount(0);

            String sql = "SELECT * FROM cliente";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("dir"),
                        rs.getInt("edad")
                });
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientesBd clientesBd = new ClientesBd();
        });
    }
}
