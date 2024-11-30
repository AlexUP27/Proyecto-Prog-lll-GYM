package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaRegistro extends JFrame {

    private static final long serialVersionUID = 1L;
    // Declaración de componentes
    private JTextField txtNombre, txtApellido, txtEdad, txtCalle, txtEmail;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar, btnCancelar;
    private JFrame vActual;

    public VentanaRegistro() {
        super();

        vActual = this;

        // Configuración de la ventana
        setTitle("Registro de Usuario");
        setBounds(300, 200, 700, 500); // Tamaño ampliado
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel principal con un diseño GridBagLayout
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espaciado

        // Estilo de las etiquetas y campos de texto
        Font etiquetaFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font campoFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

        // Etiqueta y campo para el nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        panelPrincipal.add(lblNombre, gbc);

        txtNombre = new JTextField(20);
        txtNombre.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expandir horizontalmente
        panelPrincipal.add(txtNombre, gbc);

        // Etiqueta y campo para el apellido
        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblApellido, gbc);

        txtApellido = new JTextField(20);
        txtApellido.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtApellido, gbc);

        // Etiqueta y campo para la edad
        JLabel lblEdad = new JLabel("Edad:");
        lblEdad.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblEdad, gbc);

        txtEdad = new JTextField(20);
        txtEdad.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtEdad, gbc);

        // Etiqueta y campo para la calle
        JLabel lblCalle = new JLabel("Calle:");
        lblCalle.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblCalle, gbc);

        txtCalle = new JTextField(20);
        txtCalle.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtCalle, gbc);

        // Etiqueta y campo para el email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        txtEmail.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelPrincipal.add(txtEmail, gbc);

        // Etiqueta y campo para la contraseña
        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setFont(etiquetaFont);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        panelPrincipal.add(lblContrasenia, gbc);

        txtContrasenia = new JPasswordField(20);
        txtContrasenia.setFont(campoFont);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
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
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vActual.dispose(); // Cerrar la ventana actual
            }
        });

        // Listener para el botón de "Registrarse"
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String edad = txtEdad.getText();
                String calle = txtCalle.getText();
                String email = txtEmail.getText();
                String contrasena = new String(txtContrasenia.getPassword());

                // Verificar que todos los campos estén completos
                if (nombre.isEmpty() || apellido.isEmpty() || edad.isEmpty() ||
                        calle.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(vActual, "Registro completado con éxito.", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    vActual.dispose(); // Cierra la ventana de registro
                }
            }
        });

        // Añadir paneles al frame
        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }
}