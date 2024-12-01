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
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(new Color(245, 245, 245));

        // Título en la parte superior
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(85, 107, 47));
        JLabel lblTitulo = new JLabel("Iniciar Sesión", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panel.add(panelTitulo, BorderLayout.NORTH);

        // Panel del formulario de login
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(4, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelFormulario.setBackground(new Color(245, 245, 245));

        // Componentes del formulario
        JLabel lblNombreUsuario = new JLabel("Nombre de Usuario:");
        JLabel lblContrasenia = new JLabel("Contraseña:");
        txtNombreUsuario = new JTextField(15);
        txtContraseniaUsuario = new JPasswordField(15);
        txtNombreUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
        txtContraseniaUsuario.setFont(new Font("Arial", Font.PLAIN, 14));

        // Botón de login
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogin.setBackground(new Color(85, 107, 47));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);

        // Etiqueta de error
        lblError = new JLabel();
        lblError.setForeground(Color.RED);
        lblError.setFont(new Font("Arial", Font.ITALIC, 12));

        // Añadir los componentes al panel de formulario
        panelFormulario.add(lblNombreUsuario);
        panelFormulario.add(txtNombreUsuario);
        panelFormulario.add(lblContrasenia);
        panelFormulario.add(txtContraseniaUsuario);
        panelFormulario.add(new JLabel());  // Fila vacía para el espaciado
        panelFormulario.add(lblError);
        panelFormulario.add(new JLabel());  // Fila vacía
        panelFormulario.add(btnLogin);

        // Añadir el panel de formulario al panel principal
        panel.add(panelFormulario, BorderLayout.CENTER);

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

        // Hacer visible la ventana
        add(panel);
        setVisible(true);
    }
}