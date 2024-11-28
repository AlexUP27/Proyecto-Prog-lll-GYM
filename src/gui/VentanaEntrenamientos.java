package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class VentanaEntrenamientos extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaEntrenamientos() {
        super("Tipos de Entrenamiento");
        
        // Configuración básica de la ventana
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tipos de entrenamiento
        JButton btnCardio = new JButton("Entrenamiento Cardio");
        btnCardio.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnCardio.setBackground(Color.BLUE);
        btnCardio.setForeground(Color.WHITE);
        btnCardio.setFocusPainted(false); // Sin borde al seleccionar
        
        JButton btnFuerza = new JButton("Entrenamiento de Fuerza");
        btnFuerza.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnFuerza.setBackground(Color.BLUE);
        btnFuerza.setForeground(Color.WHITE);
        btnFuerza.setFocusPainted(false); // Sin borde al seleccionar
        
        JButton btnFlexibilidad = new JButton("Entrenamiento de Flexibilidad");
        btnFlexibilidad.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnFlexibilidad.setBackground(Color.BLUE);
        btnFlexibilidad.setForeground(Color.WHITE);
        btnFlexibilidad.setFocusPainted(false); // Sin borde al seleccionar

        // Añadir botones al panel
        panelPrincipal.add(btnCardio);
        panelPrincipal.add(btnFuerza);
        panelPrincipal.add(btnFlexibilidad);
        
        // Añadir el panel a la ventana
        add(panelPrincipal);
        
        // Listeners para abrir ventanas de ejercicios específicos
        btnCardio.addActionListener(e -> abrirVentanaEjercicios("Cardio", 
            Arrays.asList("Correr", "Bicicleta", "Elíptica"),
            crearDetallesCardio(),
            crearImagenesCardio(),
            crearBrevesCardio()
        ));
        
        btnFuerza.addActionListener(e -> abrirVentanaEjercicios("Fuerza", 
            Arrays.asList("Sentadillas con Peso", "Press de Banca", "Peso Muerto", "Dominadas", "Remo con Mancuernas"),
            crearDetallesFuerza(),
            crearImagenesFuerza(),
            crearBrevesFuerza()
        ));
        
        btnFlexibilidad.addActionListener(e -> abrirVentanaEjercicios("Flexibilidad", 
            Arrays.asList("Yoga", "Estiramiento de Piernas", "Puente"),
            crearDetallesFlexibilidad(),
            crearImagenesFlexibilidad(),
            crearBrevesFlexibilidad()
        ));

        setVisible(true);
    }

	private void abrirVentanaEjercicios(String tipo, List<String> ejercicios, Map<String, String> detalles, Map<String, String> imagenes, Map<String, String> breves) {
        new VentanaEjercicios(tipo, ejercicios, detalles, imagenes, breves);
    }

	private Map<String, String> crearDetallesFuerza() {
	    Map<String, String> detalles = new HashMap<>();
	    detalles.put("Sentadillas con Peso", "1. Coloca los pies separados al ancho de los hombros.\n2. Sostén una barra o mancuernas sobre los hombros o en la espalda.\n3. Mantén el pecho hacia arriba y la espalda recta durante todo el movimiento.\n4. Baja lentamente como si estuvieras sentándote en una silla.\n5. Detente cuando las rodillas formen un ángulo de 90 grados.\n6. Empuja hacia arriba usando los talones para volver a la posición inicial.\n");
	    detalles.put("Press de Banca", "1. Acuéstate en un banco con los pies firmemente apoyados en el suelo, 2. Sostén la barra con las manos separadas un poco más del ancho de los hombros, 3. Baja la barra lentamente hacia el pecho mientras mantienes los codos hacia afuera, 4. Empuja la barra hacia arriba, extendiendo completamente los brazos, 5. Mantén el control durante todo el movimiento");
	    detalles.put("Peso Muerto", "1. Coloca los pies debajo de la barra, separados al ancho de los hombros, 2. Agarra la barra con ambas manos (agarre alterno o pronado), 3. Mantén la espalda recta y el pecho levantado, 4. Levanta la barra extendiendo las piernas y las caderas al mismo tiempo, 5. Baja la barra controladamente manteniendo una buena postura");
	    detalles.put("Dominadas", "1. Agarra la barra con las manos separadas al ancho de los hombros, 2. Mantén el cuerpo recto y los pies juntos, 3. Tira del cuerpo hacia arriba hasta que la barbilla pase por encima de la barra, 4. Baja lentamente a la posición inicial, 5. Evita balancear el cuerpo para mantener la forma");
	    detalles.put("Remo con Barra", "1. Coloca los pies separados al ancho de los hombros, 2. Agarra la barra con un agarre pronado o supinado, con las manos ligeramente más anchas que los hombros, 3. Inclina el torso hacia adelante manteniendo la espalda recta, 4. Lleva la barra hacia el abdomen contrayendo los músculos de la espalda, 5. Baja la barra controladamente a la posición inicial");
	    return detalles;
	}

    
    private Map<String, String> crearBrevesFuerza() {
        Map<String, String> breves = new HashMap<>();
        breves.put("Sentadillas con Peso", "Fortalece piernas y glúteos.");
        breves.put("Press de Banca", "Ejercicio clave para pecho y tríceps.");
        breves.put("Peso Muerto", "Trabaja la espalda baja y las piernas.");
        breves.put("Dominadas", "Fortalece la espalda y bíceps.");
        breves.put("Remo con Mancuernas", "Ideal para trabajar la espalda media.");
        return breves;
    }
    
    private Map<String, String> crearImagenesFuerza() {
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("Press de Banca", "img/press_banca.jpg");
        imagenes.put("Sentadillas con Peso", "img/sentadillas.jpeg");
        imagenes.put("Peso Muerto", "img/peso_muerto.jpeg");
        imagenes.put("Peso Muerto", "img/peso_muerto.jpeg"); //Cambiar foto
        imagenes.put("Peso Muerto", "img/peso_muerto.jpeg"); //Cambiar foto
        return imagenes;
    }

    private Map<String, String> crearDetallesFlexibilidad() {
        Map<String, String> detalles = new HashMap<>();
        detalles.put("Yoga", "El yoga combina flexibilidad y meditación.");
        detalles.put("Estiramiento de Piernas", "Aumenta el rango de movimiento en las piernas.");
        detalles.put("Puente", "Fortalece y flexibiliza la columna y caderas.");
        return detalles;
    }
    
    private Map<String, String> crearBrevesFlexibilidad() {
        Map<String, String> breves = new HashMap<>();
        breves.put("Yoga", "Una excelente forma de mejorar tu resistencia.");
        breves.put("Estiramiento de Piernas", "Ideal para fortalecer piernas y glúteos.");
        breves.put("Puente", "Ejercicio de bajo impacto para todo el cuerpo.");
        return breves;
    }

    private Map<String, String> crearImagenesFlexibilidad() {
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("Yoga", "img/yoga.jpeg");
        imagenes.put("Estiramiento de Piernas", "img/estiramiento.jpeg");
        imagenes.put("Puente", "img/puente.jpeg");
        return imagenes;
    }
    
    private Map<String, String> crearDetallesCardio() {
        Map<String, String> detalles = new HashMap<>();
        detalles.put("Correr", "1. Encuentra un lugar adecuado.\n2. Mantén una postura recta.\n3. Comienza a un ritmo cómodo.\n4. Incrementa la velocidad gradualmente.\n5. Estira después de correr.");
        detalles.put("Bicicleta", "1. Ajusta el asiento a tu altura.\n2. Coloca tus pies correctamente en los pedales.\n3. Mantén un ritmo constante.\n4. Inclínate ligeramente hacia adelante.\n5. Hidrátate bien.");
        detalles.put("Elíptica", "1. Ajusta la resistencia a un nivel cómodo.\n2. Mantén una postura erguida.\n3. Coordina brazos y piernas.\n4. Aumenta gradualmente la velocidad.\n5. Relájate al finalizar.");
        return detalles;
    }
    
    private Map<String, String> crearBrevesCardio() {
        Map<String, String> breves = new HashMap<>();
        breves.put("Correr", "Una excelente forma de mejorar tu resistencia.");
        breves.put("Bicicleta", "Ideal para fortalecer piernas y glúteos.");
        breves.put("Elíptica", "Ejercicio de bajo impacto para todo el cuerpo.");
        return breves;
    }

    private Map<String, String> crearImagenesCardio() {
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("Correr", "img/correr.jpeg");
        imagenes.put("Bicicleta", "img/bicicleta.jpeg");
        imagenes.put("Elíptica", "img/eliptica.jpeg");
        return imagenes;
    }

}
