package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.MaskFormatter;

public class InicioSesionMonitor extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnInicioSesion;
	//Declaracion de los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	private JLabel lblAutorizacionMonitor;
	private JTextField txtAutorizacionMonitor;
	//Declaramos los JFrames
	private JFrame vActual;
	
	
	//Confirmacion de cierre de ventana
	private void confirmWindowClosing() {
		int result = JOptionPane.showConfirmDialog(InicioSesionMonitor.this, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public InicioSesionMonitor(){
		vActual = this;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Monitor");
		setSize(480, 250);
		
		//Añadimos un listener para que salte la confirmacion de cierre de ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				confirmWindowClosing();
			}
		});
		
		//Instanciamos los paneles
		pCentro = new JPanel();	
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2)); //Formato de matriz de 2x2
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Instanciamos los componentes botón
		btnInicioSesion = new JButton("INICIO SESIÓN");
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblAutorizacionMonitor = new JLabel("Introduce tu autorizacion de monitor: ");
		try {
			txtAutorizacionMonitor = new JFormattedTextField(new MaskFormatter("########"));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		//Añadimos el botón al panel principal de la ventana
		pSur.add(btnInicioSesion);
		pCentro.add(lblAutorizacionMonitor);
		pCentro.add(txtAutorizacionMonitor);
		
		//Añadimos los listeners
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
		
		setVisible(true);
			
	}
	
	private void panelDeBienvenida(String user) {
        //Creamos un nuevo frame para la bienvenida
        JFrame frameDeBienvenida = new JFrame("Bienvenido");
        frameDeBienvenida.setSize(400, 200);
        frameDeBienvenida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameDeBienvenida.setLocationRelativeTo(null);
        
        //Mensaje de bienvenida (Label)
        JLabel labelDeBienvenida = new JLabel("¡Bienvenido, " + user + "!", JLabel.CENTER);
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
	}
	
}
