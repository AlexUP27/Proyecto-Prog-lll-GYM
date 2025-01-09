package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DB.BDRutinas;

public class VentanaSesionSemanal extends JFrame {

    private static final long serialVersionUID = 1L;
    Thread hilo;

    public List<String> entrenamientos = Arrays.asList("Yoga","Pilates","Spinning","Body Pump","HIIT","Power Yoga",
														"Cardio","TRX","Zumba","Boxeo","Crossfit", "Stretching") ;
    private boolean running = true;
    private final Random random = new Random();
    private int rutinaId = 0;
    

        
    public VentanaSesionSemanal() {
        setTitle("Progreso de Clientes");
        setBounds(300, 200, 1280, 550); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout()); 

        JPanel panelEntrenamientos = new JPanel(new GridLayout(1, 5));
        
        JPanel panelLunes = new JPanel(new GridLayout(2, 1));
	        JLabel imagenLunes = new JLabel();
	        
	        panelLunes.add(imagenLunes);
	        JLabel nombreLunes = new JLabel();
	        nombreLunes.setHorizontalAlignment(JLabel.CENTER);
	        panelLunes.add(nombreLunes);
        
	        
        JPanel panelMartes = new JPanel(new GridLayout(2, 1));
	        JLabel imagenMartes = new JLabel();
	        
	        panelMartes.add(imagenMartes);
	        JLabel nombreMartes = new JLabel();
	        nombreMartes.setHorizontalAlignment(JLabel.CENTER);
	        panelMartes.add(nombreMartes);
        
	        
        JPanel panelMiercoles = new JPanel(new GridLayout(2, 1));
	        JLabel imagenMiercoles = new JLabel();
	        
	        panelMiercoles.add(imagenMiercoles);
	        JLabel nombreMiercoles  = new JLabel();
	        nombreMiercoles.setHorizontalAlignment(JLabel.CENTER);
	        panelMiercoles.add(nombreMiercoles);
        
	        
        JPanel panelJueves = new JPanel(new GridLayout(2, 1));
	        JLabel imagenJueves = new JLabel();
	        
	        panelJueves.add(imagenJueves);
	        JLabel nombreJueves = new JLabel();
	        nombreJueves.setHorizontalAlignment(JLabel.CENTER);
	        panelJueves.add(nombreJueves);
        
	        
        JPanel panelViernes = new JPanel(new GridLayout(2, 1));
	        JLabel imagenViernes = new JLabel();
	        
	        panelViernes.add(imagenViernes);
	        JLabel nombreViernes = new JLabel();
	        nombreViernes.setHorizontalAlignment(JLabel.CENTER);
	        panelViernes.add(nombreViernes);
        
	    panelEntrenamientos.add(panelLunes);
	    panelEntrenamientos.add(panelMartes);
	    panelEntrenamientos.add(panelMiercoles);
	    panelEntrenamientos.add(panelJueves);
	    panelEntrenamientos.add(panelViernes);
        
	    JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton boton = new JButton("CREAR RUTINA");
        boton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        boton.setBackground(Color.RED);
        boton.setForeground(Color.WHITE);
        
        JButton boton2 = new JButton("NUEVA RUTINA");
        boton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        boton2.setBackground(Color.GREEN);
        boton2.setForeground(Color.WHITE);
        
        JButton boton3 = new JButton("GUARDAR RUTINA");
        boton3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        boton3.setBackground(Color.BLUE);
        boton3.setForeground(Color.WHITE);
        
        panelBoton.add(boton);
        panelBoton.add(boton2);
        panelBoton.add(boton3);


        add(panelEntrenamientos, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);
        
        
        crearAleatorio(imagenLunes, imagenMartes, imagenMiercoles, imagenJueves, imagenViernes, 
				       nombreLunes, nombreMartes, nombreMiercoles, nombreJueves, nombreViernes);
        
        
        boton.addActionListener(e -> {
        	running = false;
        	hilo.interrupt();
        });
        
        boton2.addActionListener( e -> {
        	if (hilo.isInterrupted()) {
        		running = true;
        		crearAleatorio(imagenLunes, imagenMartes, imagenMiercoles, imagenJueves, imagenViernes, 
        					   nombreLunes, nombreMartes, nombreMiercoles, nombreJueves, nombreViernes);
        	}
        });
        
        boton3.addActionListener(e -> {
        	
        	 // borrar/no coger recursivamente los caracteres hasta que encuentre un espacio 
        	
        	String Lunes = eliminarHastaEspacio(nombreLunes.getText());
        	String Martes = eliminarHastaEspacio(nombreMartes.getText());
        	String Miercoles = eliminarHastaEspacio(nombreMiercoles.getText());
        	String Jueves = eliminarHastaEspacio(nombreJueves.getText());
        	String Viernes = eliminarHastaEspacio(nombreViernes.getText());
        	
        	// Hacemos el insert en la tabla Rutinas
        	BDRutinas.insertarRutina(Lunes, Martes, Miercoles, Jueves, Viernes, rutinaId, BDRutinas.con);
        	
        	JOptionPane.showMessageDialog(this, "Tu rutina se ha registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        	rutinaId++;
        	
        	System.out.println(nombreLunes.getText() + nombreMartes.getText() + nombreMiercoles.getText() + nombreJueves.getText()+ nombreViernes.getText());
        });
        
        setVisible(true);    
 
    }
    
    public void crearAleatorio(JLabel imagenLunes, JLabel imagenMartes, JLabel imagenMiercoles, 
    								JLabel imagenJueves, JLabel imagenViernes, JLabel nombreLunes, 
    							JLabel nombreMartes, JLabel nombreMiercoles, JLabel nombreJueves, JLabel nombreViernes) {
    	
    	hilo = new Thread(() -> {
			
    		while (running) {
    			
    			List<String> randomEntrenamientos = new ArrayList<>();               
    			for (int i = 0; i < 5; i++) {
    	        randomEntrenamientos.add(entrenamientos.get(random.nextInt(entrenamientos.size())));
    			}
    			
    			ImageIcon iconoLunes = new ImageIcon("img/" + randomEntrenamientos.get(0) + ".png");
    	        imagenLunes.setIcon(iconoLunes);
    	        nombreLunes.setText("Lunes: " +randomEntrenamientos.get(0));
    	        ImageIcon iconoMartes = new ImageIcon("img/" + randomEntrenamientos.get(1) + ".png");
    	        imagenMartes.setIcon(iconoMartes);
    	        nombreMartes.setText("Martes: " +randomEntrenamientos.get(1));
    	        ImageIcon iconoMiercoles = new ImageIcon("img/" + randomEntrenamientos.get(2) + ".png");
    	        imagenMiercoles.setIcon(iconoMiercoles);
    	        nombreMiercoles.setText("Miercoles: " +randomEntrenamientos.get(2));                
    	        ImageIcon iconoJueves = new ImageIcon("img/" + randomEntrenamientos.get(3) + ".png");
    	        imagenJueves.setIcon(iconoJueves);
    	        nombreJueves.setText("Jueves: " +randomEntrenamientos.get(3));
    	        ImageIcon iconoViernes = new ImageIcon("img/" + randomEntrenamientos.get(4) + ".png");
    	        imagenViernes.setIcon(iconoViernes);
    	        nombreViernes.setText("Viernes: " +randomEntrenamientos.get(4));
                
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
        }
    	});

    	hilo.start();
    	
    }
    
    public static String eliminarHastaEspacio(String Ejercicio) {
    	// Caso base: si el texto está vacío o no contiene espacio, devuelve el texto completo.
        if (Ejercicio.isEmpty() || Ejercicio.charAt(0) == ' ') {
            return Ejercicio.trim(); //Utilizamos texto.trim para que cuando encuentre el espacio: 
            					 //devuelva el string sin el espacio
        }
        // Llamada recursiva eliminando el primer carácter.
        return eliminarHastaEspacio(Ejercicio.substring(1));
    }
    
    
}