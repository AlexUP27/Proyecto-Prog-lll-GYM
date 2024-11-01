package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class InicioSesionMonitor extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnInicioSesion,btnAtras ;
	//Declaracion de los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	private JLabel lblAutorizacionMonitor;
	private JTextField txtAutorizacionMonitor;
	//Declaramos los JFrames
	private JFrame vActual;
	
	
	public InicioSesionMonitor() {
        vActual = this;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Monitor");
        setSize(480, 250);
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Añadimos un listener para que salte la confirmación de cierre de ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmWindowClosing();
            }
        });

        // Instanciamos los paneles
        pCentro = new JPanel();
        pNorte = new JPanel();
        pSur = new JPanel();
        pEste = new JPanel();
        pOeste = new JPanel();
        
        // Modificamos el Layout del panel centro
        pCentro.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado alrededor de los componentes
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los componentes

        // Añadimos los paneles al panel principal de la ventana
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pEste, BorderLayout.EAST);
        getContentPane().add(pOeste, BorderLayout.WEST);
        getContentPane().add(pSur, BorderLayout.SOUTH);
        getContentPane().add(pCentro, BorderLayout.CENTER);

        // Instanciamos los componentes
        btnInicioSesion = new JButton("INICIO SESIÓN");
        btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnInicioSesion.setBackground(Color.BLUE);
        btnInicioSesion.setForeground(Color.WHITE);
        btnInicioSesion.setFocusPainted(false); // Quitar el borde del botón

        lblAutorizacionMonitor = new JLabel("Introduce tu autorización de monitor: ");
        lblAutorizacionMonitor.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        // Usamos JTextField en vez de JFormattedTextField para simplicidad
        txtAutorizacionMonitor = new JTextField(20);

        // Crear el botón de "Atrás" en la parte superior izquierda
        btnAtras = new JButton("Atrás");
        btnAtras.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnAtras.setBackground(Color.RED);
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFocusPainted(false); // Quitar el borde del botón

        // Añadimos los componentes al panel de centro
        gbc.gridx = 0;
        gbc.gridy = 0;
        pCentro.add(lblAutorizacionMonitor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pCentro.add(txtAutorizacionMonitor, gbc);

        // Añadimos los botones
        pSur.add(btnAtras);
        pSur.add(btnInicioSesion);

        // Listener para el botón de "Atrás"
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vActual.dispose();   // Cerrar la ventana actual
            }
        });

        // Añadimos los listeners
        btnInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autorizacion = txtAutorizacionMonitor.getText();
                if (autorizacion.isEmpty()) {
                    JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                } else {
                    panelDeBienvenida(autorizacion);
                }
            }
        });

        // Cambiar el icono de la ventana (Esquina superior izquierda)
        ImageIcon imagen = new ImageIcon("img/icono.png");
        setIconImage(imagen.getImage());

        // Hacer visible todo
        setVisible(true);
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
	
	private void openNewWindow() {
	    // Crear la nueva ventana
	    JFrame nuevaVentana = new JFrame("Ventana Monitores");
	    nuevaVentana.setSize(400, 300);
	    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    nuevaVentana.setLocationRelativeTo(null);

	    // Crear el panel principal con un BorderLayout
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

	    // Crear un botón de "Información Monitores" y colocarlo en la parte inferior
	    JButton botonMonitores = new JButton("Información Monitores");
	    botonMonitores.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
	    botonMonitores.setBackground(Color.BLUE);
	    botonMonitores.setForeground(Color.WHITE);
	    botonMonitores.setFocusPainted(false); // Sin borde al seleccionar

	    // Panel para el botón de "Información Monitores"
	    JPanel panelInferior = new JPanel();
	    panelInferior.setBackground(Color.WHITE);
	    panelInferior.add(botonMonitores);

	    // Añadir el panel inferior al panel principal
	    panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

	    // Establecer el panel principal como el contenido de la ventana
	    nuevaVentana.add(panelPrincipal);
	    
	    // Hacer visible la nueva ventana
	    nuevaVentana.setVisible(true);

	    // Listener para el botón de "Cerrar Sesión"
	    btnCerrarSesion.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            nuevaVentana.dispose();  // Cerrar la nueva ventana
	            vActual.setVisible(true); // Mostrar de nuevo la ventana de inicio de sesión
	        }
	    });

	    // Listener para el botón de "Información Monitores"
	    botonMonitores.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            InformacionMonitores insertar = new InformacionMonitores();		
	        }
	    });
	}


	
	//Confirmacion de cierre de ventana
	private void confirmWindowClosing() {
		int result = JOptionPane.showConfirmDialog(InicioSesionMonitor.this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
}
