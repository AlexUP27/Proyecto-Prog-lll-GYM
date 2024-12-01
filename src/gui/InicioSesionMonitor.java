package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesionMonitor extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombreUsuario;
    private JPasswordField txtContraseniaUsuario;
    private JButton btnLogin;
    private JLabel lblError;

    public InicioSesionMonitor() {
        setTitle("Inicio de Sesión Monitor");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para el formulario de login
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Componentes de la ventana
        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        JLabel lblContrasenia = new JLabel("Contraseña:");

        txtNombreUsuario = new JTextField();
        txtContraseniaUsuario = new JPasswordField();

        btnLogin = new JButton("Iniciar Sesión");
        lblError = new JLabel();
        lblError.setForeground(Color.RED);

        // Añadir componentes al panel
        panel.add(lblNombreUsuario);
        panel.add(txtNombreUsuario);
        panel.add(lblContrasenia);
        panel.add(txtContraseniaUsuario);
        panel.add(new JLabel());  // Fila vacía para el espaciado
        panel.add(lblError);
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);

        // Acción del botón de login
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtNombreUsuario.getText();
                String contrasena = new String(txtContraseniaUsuario.getPassword());

                if (!usuario.isEmpty() && !contrasena.isEmpty()) {
                    // Verificación simple (puedes hacerla más compleja con bases de datos, etc.)
                    if (usuario.equals("monitor") && contrasena.equals("password")) {
                        // Si el login es correcto, abre el menú principal
                        new MenuPrincipalMonitor();
                        dispose(); // Cierra la ventana de inicio de sesión
                    } else {
                        lblError.setText("Usuario o contraseña incorrectos.");
                    }
                } else {
                    lblError.setText("Por favor, complete todos los campos.");
                }
            }
        });

        setVisible(true);
    }
}