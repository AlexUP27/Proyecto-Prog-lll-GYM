package gui;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import graphics.VentanaConFondobis;

public class VentanaInicial extends JFrame {
 
    private static final long serialVersionUID = 1L;
    // Declaración de los componentes botón
    private JButton btnInicioSesion, botonMonitor, btnRegistro;
    // Declaración de los componentes etiqueta
    private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
    // Declaración de los componente cuadro de texto
    private JTextField txtNombreUsuario;
    private JPasswordField txtContraseniaUsuario;
    // Declaración de los paneles
    private JPanel pCentro, pCentroCentro, pCentroSur, pNorte;
    // Declaramos los JFrames
    private JFrame vActual;
    
	int zabaleraPantaila, altueraPantaila;
	Image fondo;
	JLabel lBalioa;
	

    public VentanaInicial() {
        super();
       
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		zabaleraPantaila = (int) toolkit.getScreenSize().getWidth();
		altueraPantaila = (int) toolkit.getScreenSize().getHeight();
		this.setSize(zabaleraPantaila, altueraPantaila);
		this.setIconImage(new ImageIcon("irudiak/logo.png").getImage());
		fondo = toolkit.createImage("img/fondo.jpeg");
   	    this.setContentPane(sortuLeihokoPanela());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vActual = this;
       
        setBounds(375, 150, 620, 600);
        setTitle("DEUSTO GYM");
        

        ImageIcon imagen = new ImageIcon("img/icono.png");
        setIconImage(imagen.getImage());

        pCentro = new JPanel();
        pCentro.setLayout(new BorderLayout());
        pCentroCentro = new JPanel();
        pCentroCentro.setLayout(new GridLayout(2,2));
        pCentroSur = new JPanel();
        pCentroSur.setLayout(new GridLayout(1,2));
        pNorte = new JPanel();
        pNorte.setLayout(new GridLayout());

       
        lblTitulo = new JLabel("WELLCOME!! ONGI ETORRI!! BIENVENIDO!!");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        lblTitulo.setForeground(Color.GREEN);
        pCentro.add(lblTitulo, BorderLayout.NORTH);

       
        lblNombreUsuario = new JLabel("Introduce tu nombre de usuario:");
        lblNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        pCentroCentro.add(lblNombreUsuario, BorderLayout.CENTER);

        txtNombreUsuario = new JTextField(20);
        pCentroCentro.add(txtNombreUsuario, BorderLayout.CENTER);

        lblContraseniaUsuario = new JLabel("Introduce tu contraseña:");
        lblContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        pCentroCentro.add(lblContraseniaUsuario, BorderLayout.CENTER);

        txtContraseniaUsuario = new JPasswordField(20);
        pCentroCentro.add(txtContraseniaUsuario, BorderLayout.CENTER);

        
        btnInicioSesion = new JButton("INICIO SESIÓN");
        btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnInicioSesion.setBackground(Color.BLUE);
        btnInicioSesion.setForeground(Color.WHITE);
        btnInicioSesion.setFocusPainted(false);
        pCentroSur.add(btnInicioSesion);
        
        btnRegistro = new JButton("REGISTRARSE");
        btnRegistro.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnRegistro.setBackground(Color.ORANGE);
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFocusPainted(false);
        pCentroSur.add(btnRegistro);

        botonMonitor = new JButton("Monitor");
        botonMonitor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        botonMonitor.setBackground(Color.LIGHT_GRAY);
        botonMonitor.setForeground(Color.BLACK);
        botonMonitor.setFocusPainted(false);
        botonMonitor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        botonMonitor.addActionListener(e -> new InicioSesionMonitor());
        
        pNorte.add(botonMonitor);
        pNorte.setBackground(Color.LIGHT_GRAY);

       
        getContentPane().add(pNorte);
        getContentPane().add(pCentro);
        pCentro.add(pCentroCentro, BorderLayout.CENTER);
        pCentro.add(pCentroSur, BorderLayout.SOUTH);

    
        btnInicioSesion.addActionListener(e -> {
            String user = txtNombreUsuario.getText();
            String contrasena = new String(txtContraseniaUsuario.getPassword());

            // Verificar si los campos están vacíos
            if (user.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar si los datos coinciden con los de la base de datos
            if (usuarioValido(user, contrasena)) {
                panelDeBienvenida(user);
            } else {
                // Mostrar error en caso de que no coincidan
                JOptionPane.showMessageDialog(vActual, "Error al iniciar sesión. Compruebe si los datos están bien escritos. Si no está registrado, pulse el botón de registro para hacerlo.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            }
        });
	    
     // Listener para el botón de "Registrarse"
        btnRegistro.addActionListener(e -> new VentanaRegistro());
        
        setVisible(true);
    }
	
    private boolean usuarioValido(String username, String password) {
        // Consulta SQL para filtrar la tabla segun el nombre de usuario y la contraseña
        String query = "SELECT * FROM ContraseñasInicioSesion WHERE Nom_usuario = ? AND contraseña = ?";
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:Gym.db");
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password); 

            ResultSet rs = stmt.executeQuery();
            
            return rs.next(); 
            
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        
        return false;
    }

    private void panelDeBienvenida(String username) {
        // Creamos un nuevo frame para la bienvenida
        JFrame frameDeBienvenida = new JFrame("Bienvenido");
        frameDeBienvenida.setSize(400, 200);
        frameDeBienvenida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDeBienvenida.setLocationRelativeTo(null); // Centrar el frame en la pantalla

        // Configurar el layout del frame
        JPanel panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Mensaje de bienvenida (Label)
        JLabel labelDeBienvenida = new JLabel("¡Bienvenido, " + username + "!", JLabel.CENTER);
        labelDeBienvenida.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        labelDeBienvenida.setForeground(Color.GREEN);

        // Añadimos el label al panel
        gbc.gridx = 0; // Columna 0
        gbc.gridy = 0; // Fila 0
        panelBienvenida.add(labelDeBienvenida, gbc);

        // Establecemos un color de fondo para el panel
        panelBienvenida.setBackground(Color.WHITE);
        frameDeBienvenida.add(panelBienvenida, BorderLayout.CENTER); // Añadimos el panel al frame

        // Hacer visible el frame
        frameDeBienvenida.setVisible(true);

        // Temporizador para que desaparezca la ventana de bienvenida al de 3 segundos 
        Timer timer = new Timer(2000, e -> {
            frameDeBienvenida.dispose();  // Cierra la ventana
            new VentanaNewWindow(username);
        });

        timer.setRepeats(false);  // Poner el temporizador
        timer.start();            // Empieza el temporizador

        // Hacer invisible la ventana del main
        vActual.setVisible(false);
    }
  	
  	public Container sortuLeihokoPanela() {
		VentanaConFondobis panel = new VentanaConFondobis(fondo);
		return panel;
	}


    

}
