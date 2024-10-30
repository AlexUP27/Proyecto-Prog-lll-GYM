package gui;
	
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;


public class VentanaInicial extends JFrame{
	//Declaracion de los componentes botón
		private JButton btnInicioSesion, btnCierreSesion;
		//Declaracion de los componentes etiqueta
		private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
		//Declaracion de los componente cuadro de texto
		private JTextField txtNombreUsuario;
		private JPasswordField txtContraseniaUsuario;
		//Declaracion de los paneles
		private JPanel pCentro, pNorte, pEste, pOeste, pSur;
		//Declaramos los JFrames
		private JFrame vActual;
		
	
	public VentanaInicial() {
		super();
		
		vActual = this;
		
		//Añadimos un listener para que salte la confirmacion de cierre de ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmWindowClosing();
			}
		});
		
		//Establecemos las propiedades de la ventana
		setBounds(300, 200, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Cambiar el título de la ventana
		setTitle("DEUSTO GYM");
		
		//Cambiar el icono de la ventana (Esquina superior izquierda)
		ImageIcon imagen = new ImageIcon("img/icono.png");
		setIconImage(imagen.getImage());
		
//		//Inicializacion del boton informacion clientes
//		JButton botonClientes = new JButton("informacion clientes");
		
		//Inicializacion del boton inicio sesion monitor
		JButton botonMonitor = new JButton("Monitor");
		
		//Instanciamos los paneles
		pCentro = new JPanel();
		
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2)); //Formato de matriz de 2x2
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		
		//Listener y posicion del boton clientes
		pNorte.add(botonMonitor,BorderLayout.WEST);	
		botonMonitor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InicioSesionMonitor insertar = new InicioSesionMonitor();				
			}
		});
		
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Instanciamos los componentes botón
		btnInicioSesion = new JButton("INICIO SESIÓN");
		//btnCierreSesion = new JButton("CIERRE SESIÓN");
		
		//Cambiamos el tipo de letra en los botones
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		//btnCierreSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
		lblTitulo = new JLabel("ONGI ETORRI!!");
		lblNombreUsuario = new JLabel("Introduce tu nombre de usuario: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);
		
		//Añadimos el botón al panel principal de la ventana
		pSur.add(btnInicioSesion);
		//pSur.add(btnCierreSesion);
		pNorte.add(lblTitulo);
		//Como pCentro es un GridLayout, hay que añadir los componentes en el orden en el que queremos que aparezcan (De izda a dcha y de arriba a abajo)
		pCentro.add(lblNombreUsuario);
		pCentro.add(txtNombreUsuario);
		pCentro.add(lblContraseniaUsuario);
		pCentro.add(txtContraseniaUsuario);
		
		
		//Añadimos los listeners
        btnInicioSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtNombreUsuario.getText();
                String contrasena = new String(txtContraseniaUsuario.getPassword());
                
                if (user.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(vActual, "Por favor, complete todos los campos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                } else {
                	panelDeBienvenida(user);
                }
            }
        });
        
        setVisible(true);
	}
	
	
	private void panelDeBienvenida(String username) {
        //Creamos un nuevo frame para la bienvenida
        JFrame frameDeBienvenida = new JFrame("Bienvenido");
        frameDeBienvenida.setSize(400, 200);
        frameDeBienvenida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDeBienvenida.setLocationRelativeTo(null);
        
        //Mensaje de bienvenida (Label)
        JLabel labelDeBienvenida = new JLabel("¡Bienvenido, " + username + "!", JLabel.CENTER);
        labelDeBienvenida.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        
        //Añadimos el label al frame y lo hacemos visible
        frameDeBienvenida.add(labelDeBienvenida, BorderLayout.CENTER);
        frameDeBienvenida.setVisible(true);
        
        //Temporizador para que desaparezca la ventana de bienvenida al de 3 segundos 
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frameDeBienvenida.dispose();  //Cierra la ventana
            	openNewWindow(); 
            }
        });
        timer.setRepeats(false);  //Poner el temporiador
        timer.start();            //Empieza el temporizador
        
        // Hacer invisible la ventana del main
        vActual.setVisible(false);
        
    }
	
	// Método para abrir una nueva ventana después de la bienvenida
    private void openNewWindow() {
    	// Crear la nueva ventana
        JFrame nuevaVentana = new JFrame("Nueva Ventana");
        nuevaVentana.setSize(400, 300);
        nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nuevaVentana.setLocationRelativeTo(null);

        // Crear el botón de "Cerrar Sesión" en la esquina superior derecha
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        
        // Panel para el botón en la parte superior derecha
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior.add(btnCerrarSesion);
        
        // Añadir el panel y la etiqueta a la nueva ventana
        nuevaVentana.add(panelSuperior, BorderLayout.NORTH);
        nuevaVentana.setVisible(true);

        // Listener para el botón de "Cerrar Sesión"
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	nuevaVentana.dispose();  // Cerrar la nueva ventana
                vActual.setVisible(true);  // Mostrar de nuevo la ventana de inicio de sesión
            }
        });
        
        //Inicializacion del boton informacion clientes
      	JButton botonClientes = new JButton("Informacion Clientes");
      	botonClientes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
      	
      	// Panel para el botón en la parte superior derecha
      	JPanel panelSuperior2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelSuperior2.add(botonClientes);
        
        // Añadir el panel a la nueva ventana
        nuevaVentana.add(panelSuperior2, BorderLayout.SOUTH);
        nuevaVentana.setVisible(true);
        
        //Listener y posicion del boton clientes	
        botonClientes.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		InformacionClientes insertar = new InformacionClientes();		
        	}
        }); 
    }
    //Confirmacion de cierre de ventana
  	private void confirmWindowClosing() {
  		int result = JOptionPane.showConfirmDialog(VentanaInicial.this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
  		if (result == JOptionPane.YES_OPTION) {
  			System.exit(0);
  		}
  	}
}
