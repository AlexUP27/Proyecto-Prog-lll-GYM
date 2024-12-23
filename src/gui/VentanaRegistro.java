package gui;

import javax.swing.*;

import DB.BDInicioSesion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;

    // Declaración de los componentes
    private JTextField txtEmail;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar, btnCancelar;
    private JFrame vActual;

    public VentanaRegistro() {
        super();

        vActual = this;

        // Configuración de la ventana
        setTitle("Registro de Usuario");
        setBounds(300, 200, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado

        // Fuente y color personalizados
        Font etiquetaFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font campoFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

        // Estilo de las etiquetas
        JLabel lblEmail = new JLabel("Email (Usuario):");
        lblEmail.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        panelPrincipal.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        txtEmail.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expande horizontalmente
        panelPrincipal.add(txtEmail, gbc);

        // Estilo de las contraseñas
        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        panelPrincipal.add(lblContrasenia, gbc);

        txtContrasenia = new JPasswordField(20);
        txtContrasenia.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expande horizontalmente
        panelPrincipal.add(txtContrasenia, gbc);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.LIGHT_GRAY);

        // Botón de registro
        btnRegistrar = new JButton("REGISTRARSE");
        btnRegistrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnRegistrar.setBackground(Color.BLUE);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        panelBotones.add(btnRegistrar);

        // Botón de cancelar
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnCancelar.setBackground(Color.RED);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        panelBotones.add(btnCancelar);

        // Listener para el botón de "Cancelar"
        btnCancelar.addActionListener(e -> vActual.dispose()); 

        // Listener para el botón de "Registrarse"
        btnRegistrar.addActionListener(e -> {
            String usuario = txtEmail.getText();
            String contrasena = new String(txtContrasenia.getPassword());
        
            // Verificar que el nombre de usuario y la contraseña no estén vacíos
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            // Verificar si el usuario ya existe en la base de datos
            if (BDInicioSesion.existeUsuario(usuario)) {
                JOptionPane.showMessageDialog(vActual, "El usuario ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Si no existe, agregar el nuevo usuario a la base de datos
                BDInicioSesion.insertarUsuario(usuario, contrasena);
                JOptionPane.showMessageDialog(vActual, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vActual.dispose(); // Cerrar la ventana después de registrar
            }
        });

        
        // 3 intento commit y push

        // Añadir paneles al frame
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }
}
