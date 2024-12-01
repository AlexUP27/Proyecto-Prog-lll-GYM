package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class VentanaInicial extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Declaración de los componentes botón
    private JButton btnInicioSesion, btnCierreSesion, botonMonitor, btnRegistro;
    // Declaración de los componentes etiqueta
    private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
    // Declaración de los componente cuadro de texto
    private JTextField txtNombreUsuario;
    private JPasswordField txtContraseniaUsuario;
    // Declaración de los paneles
    private JPanel pCentro, pNorte;
    // Declaramos los JFrames
    private JFrame vActual;

    public VentanaInicial() {
        super();

        vActual = this;

        // Añadimos un listener para que salte la confirmación de cierre de ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmWindowClosing();
            }
        });

        // Establecemos las propiedades de la ventana
        setBounds(300, 200, 600, 400);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("DEUSTO GYM");

        // Cambiar el icono de la ventana (Esquina superior izquierda)
        ImageIcon imagen = new ImageIcon("img/icono.png");
        setIconImage(imagen.getImage());

        // Inicializamos los paneles
        pCentro = new JPanel();
        pNorte = new JPanel();

        // Usamos GridBagLayout para el panel central
        pCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Espaciado

        // Establecemos el título de bienvenida
        lblTitulo = new JLabel("ONGI ETORRI!!");
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 28));
        lblTitulo.setForeground(Color.GREEN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa 2 columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el título
        pCentro.add(lblTitulo, gbc);

        // Etiquetas y campos de texto
        lblNombreUsuario = new JLabel("Introduce tu nombre de usuario:");
        lblNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        pCentro.add(lblNombreUsuario, gbc);

        txtNombreUsuario = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        pCentro.add(txtNombreUsuario, gbc);

        lblContraseniaUsuario = new JLabel("Introduce tu contraseña:");
        lblContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        pCentro.add(lblContraseniaUsuario, gbc);

        txtContraseniaUsuario = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        pCentro.add(txtContraseniaUsuario, gbc);

        // Inicialización del botón de inicio de sesión
        btnInicioSesion = new JButton("INICIO SESIÓN");
        btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnInicioSesion.setBackground(Color.BLUE);
        btnInicioSesion.setForeground(Color.WHITE);
        btnInicioSesion.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1; // Ocupa 2 columnas
        gbc.fill = GridBagConstraints.HORIZONTAL;
        pCentro.add(btnInicioSesion, gbc);
        
     // Inicialización del botón de registro
        btnRegistro = new JButton("REGISTRARSE");
        btnRegistro.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnRegistro.setBackground(Color.ORANGE);
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.setFocusPainted(false);
        gbc.gridx = 1;  // Segunda columna, misma fila
        gbc.gridy = gbc.gridy; // Mismo nivel que el botón de inicio de sesión
        gbc.gridwidth = 1; // Ocupa una sola columna
        pCentro.add(btnRegistro, gbc);

        // Inicialización del botón de monitor
        botonMonitor = new JButton("Monitor");
        botonMonitor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        botonMonitor.setBackground(Color.LIGHT_GRAY);
        botonMonitor.setForeground(Color.BLACK);
        botonMonitor.setFocusPainted(false);
        botonMonitor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Añadir el botón al panel norte
        pNorte.add(botonMonitor);
        botonMonitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesionMonitor insertar = new InicioSesionMonitor();
            }
        });

        // Estilo del panel norte
        pNorte.setBackground(Color.LIGHT_GRAY);

        // Añadimos los paneles al panel principal de la ventana
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pCentro, BorderLayout.CENTER);

        // Añadimos los listeners
        btnInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtNombreUsuario.getText();
                String contrasena = new String(txtContraseniaUsuario.getPassword());

                //Verificar si los campos están vacios
                if (user.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Verificar si los datos coinciden con los de la base de datos
                if (usuarioValido(user, contrasena)) {
                    panelDeBienvenida(user);
                } else {
                    // Mostrar error en caso de que no coincidan
                    JOptionPane.showMessageDialog(vActual, "Error al iniciar sesión. Compruebe si los datos están bien escritos. Si no está registrado, pulse el botón de registro para hacerlo.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
     // Listener para el botón de "Registrarse"
        btnRegistro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaRegistro ventanaRegistro = new VentanaRegistro();
            }
        });

        setVisible(true);
    }
	
	//segundo intento de push
    
    private boolean usuarioValido(String username, String password) {
        // Consulta SQL para filtrar la tabla segun el nombre de usuario y la contraseña
        String query = "SELECT * FROM ContraseñasInicioSesion WHERE Nom_usuario = ? AND contraseña = ?";
        
        // Establecer la conexión a la base de datos SQLite
        try (Connection con = DriverManager.getConnection("jdbc:sqlite:Gym.db");
             PreparedStatement stmt = con.prepareStatement(query)) {
            
            // Establece los parametros de la consulta
            stmt.setString(1, username); // Asigna el nombre de usuario
            stmt.setString(2, password); // Asigna la contraseña

            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery();
            
            // Si el resultado tiene alguna fila, el usuario es valido
            return rs.next(); 
            
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime cualquier error de SQL
        }
        
        // Si no se encuentra un usuario valido, devolver false
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
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameDeBienvenida.dispose();  // Cierra la ventana
                openNewWindow(); 
            }
        });
        timer.setRepeats(false);  // Poner el temporizador
        timer.start();            // Empieza el temporizador

        // Hacer invisible la ventana del main
        vActual.setVisible(false);
    }

	
    // Método para abrir una nueva ventana después de la bienvenida
    private void openNewWindow() {
        // Crear la nueva ventana
        JFrame nuevaVentana = new JFrame("Ventana Clientes");
        nuevaVentana.setSize(400, 300);
        nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaVentana.setLocationRelativeTo(null);

        // Crear un panel principal con un BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        
        // Crear el botón de "Cerrar Sesión" en la esquina superior derecha
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnCerrarSesion.setBackground(Color.RED);
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setFocusPainted(false); // Sin borde al seleccionar

        // Panel para el botón en la parte superior derecha
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.setBackground(Color.LIGHT_GRAY);
        panelSuperior.add(btnCerrarSesion);
        
        // Añadir el panel superior al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        
        // Añadir un boton o algo proximamente
        JLabel lblMensaje = new JLabel("Añadir algo aqui", JLabel.CENTER);
        lblMensaje.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        panelPrincipal.add(lblMensaje, BorderLayout.CENTER);

        // Panel para los botones en la parte inferior
        JPanel panelInferior = new JPanel(new FlowLayout());
        panelInferior.setBackground(Color.WHITE);

        // Inicialización del botón "Información Clientes"
        JButton botonClientes = new JButton("Gestion Clientes");
        botonClientes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        botonClientes.setBackground(Color.BLUE);
        botonClientes.setForeground(Color.WHITE);
        botonClientes.setFocusPainted(false); // Sin borde al seleccionar
        panelInferior.add(botonClientes);

        // Inicialización del botón "Horario Clases"
        JButton botonHorario = new JButton("Horario Clases");
        botonHorario.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        botonHorario.setBackground(Color.BLUE);
        botonHorario.setForeground(Color.WHITE);
        botonHorario.setFocusPainted(false); // Sin borde al seleccionar
        panelInferior.add(botonHorario);
        
        //Inicializacion del boton "Gestion de Pagos"
        JButton botonPagos = new JButton("Gestion de Pagos");
        botonPagos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        botonPagos.setBackground(Color.BLUE);
        botonPagos.setForeground(Color.WHITE);
        botonPagos.setFocusPainted(false); // Sin borde al seleccionar
        panelInferior.add(botonPagos);
        
        // Panel para boton gestion de pagos
        JPanel panelBotonesClientes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotonesClientes.add(botonPagos);
        panelPrincipal.add(panelBotonesClientes, BorderLayout.CENTER);
        
        //Inicializacion del boton "Boton de Entrenamiento"
        JButton btnEntrenamiento = new JButton("Entrenamiento");
        btnEntrenamiento.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnEntrenamiento.setBackground(Color.BLUE);
        btnEntrenamiento.setForeground(Color.WHITE);
        btnEntrenamiento.setFocusPainted(false); // Sin borde al seleccionar
        
        // Agregar al panel de botones el boton de entrenamiento
        panelBotonesClientes.add(btnEntrenamiento);
        
        //Inicializacion del boton "Seguimiento de Progreso"
        JButton btnSeguimientoProgreso = new JButton("Seguimiento de Progreso");
        btnSeguimientoProgreso.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnSeguimientoProgreso.setBackground(Color.BLUE);
        btnSeguimientoProgreso.setForeground(Color.WHITE);
        btnSeguimientoProgreso.setFocusPainted(false); // Sin borde al seleccionar
        
        // Agregar al panel de botones el boton de Seguimiento de Progreso
        panelBotonesClientes.add(btnSeguimientoProgreso);

        // Añadir el panel inferior al panel principal
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
        
        // Establecer el panel principal como el contenido de la ventana
        nuevaVentana.add(panelPrincipal);
        nuevaVentana.setVisible(true);

        // Listener para el botón de "Cerrar Sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaVentana.dispose();  // Cerrar la nueva ventana
                vActual.setVisible(true); // Mostrar de nuevo la ventana de inicio de sesión
            }
        });

        // Listener y posición del botón "Información Clientes"
        botonClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientesBd insertar = new ClientesBd();
            }
        });

        // Listener y posición del botón "Horario Clases"
        botonHorario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HorarioClases insertar = new HorarioClases();
            }
        });
        
        // Listener y posición del botón "Gestión de Pagos"
        botonPagos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionPagos gestionPagos = new GestionPagos();
            }
        });
        
        // Listener y posición del botón de "Entrenamiento"
        btnEntrenamiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaEntrenamientos();  // Abre la ventana de tipos de entrenamiento
                vActual.setVisible(false);
            }
        });
        
        // Listener y posición del botón de "Seguimiento de Progreso"
        btnSeguimientoProgreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	SeguimientoProgreso seguimientoProgreso = new SeguimientoProgreso();
            }
        });
    }
    //solucionar proble con el push y commit


    
    //Confirmacion de cierre de ventana
  	private void confirmWindowClosing() {
  		int result = JOptionPane.showConfirmDialog(VentanaInicial.this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
  		if (result == JOptionPane.YES_OPTION) {
  			System.exit(0);
  		}
  	}
}
