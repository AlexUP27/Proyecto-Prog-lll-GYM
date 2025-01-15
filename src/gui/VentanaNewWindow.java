package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DB.ClientesBd;
import DB.DiasSemanaBD;


public class VentanaNewWindow {
	
	String entrenamiento;
	
	public VentanaNewWindow(String user) {
		
	
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
     
     JButton botonEntrenamientoRandom = new JButton("Entrenamiento Aleatorio");
     botonEntrenamientoRandom.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
     botonEntrenamientoRandom.setBackground(Color.BLUE);
     botonEntrenamientoRandom.setForeground(Color.WHITE);
     botonEntrenamientoRandom.setFocusPainted(false); // Sin borde al seleccionar
     panelBotonesClientes.add(botonEntrenamientoRandom);
     
     JButton botonCombinatoria = new JButton("Combinatoria de Entrenamientos");
     botonCombinatoria.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
     botonCombinatoria.setBackground(Color.BLUE);
     botonCombinatoria.setForeground(Color.WHITE);
     botonCombinatoria.setFocusPainted(false); // Sin borde al seleccionar
     panelBotonesClientes.add(botonCombinatoria);
     
     //Inicialización del botón "Tabata"
     JButton botonTabata = new JButton("Tabata");
     botonTabata.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
     botonTabata.setBackground(Color.BLUE);
     botonTabata.setForeground(Color.WHITE);
     botonTabata.setFocusPainted(false); // Sin borde al seleccionar
     panelInferior.add(botonTabata);
     
     // Añadir el panel inferior al panel principal
     panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
     
     // Establecer el panel principal como el contenido de la ventana
     nuevaVentana.add(panelPrincipal);
     nuevaVentana.setVisible(true);
	    
     // Listener para el botón de "Cerrar Sesión"
     btnCerrarSesion.addActionListener(e -> {
         nuevaVentana.dispose();  // Cerrar la nueva ventana
         new VentanaInicial(); // Mostrar de nuevo la ventana de inicio de sesión
     });
	    
     // Listener y posición del botón "Información Clientes"
     botonClientes.addActionListener(e -> new ClientesBd());
     
     // Listener y posición del botón "Horario Clases"
     botonHorario.addActionListener(e -> new VentanaSemana());
     
     // Listener y posición del botón "GestionPagos"
     botonPagos.addActionListener(e -> new GestionPagos());

     // Listener y posición del botón "Entrenamientos"
     btnEntrenamiento.addActionListener(e -> new VentanaEntrenamientos());

     // Listener y posición del botón de "Seguimiento de Progreso"
     btnSeguimientoProgreso.addActionListener(e -> new SeguimientoProgreso());
	 
     botonEntrenamientoRandom.addActionListener(e -> new VentanaSesionSemanal());
     
     botonCombinatoria.addActionListener(e -> {
     entrenamiento = pedirEntrenamiento();
     if (VentanaCombinaciones.entrenamientos.contains(entrenamiento)) new VentanaCombinaciones(entrenamiento);
     });
     
     // Listener para el botón "Tabata"
     botonTabata.addActionListener(e -> new VentanaTabata());
	}
	
	public String pedirEntrenamiento() {
		String entrenamiento = JOptionPane.showInputDialog(null, "Cual es el entrenamiento con el que quieres empezar la semana?", 
                											"Solicitud de entrenamiento", JOptionPane.QUESTION_MESSAGE);
		
	    if (!VentanaCombinaciones.entrenamientos.contains(entrenamiento)) {
	    	JOptionPane.showMessageDialog(null, "Ese entrenamiento no está disponible", "Resultado", JOptionPane.INFORMATION_MESSAGE);
	    	return null;
	    } else return entrenamiento;
    }

	
	
}
