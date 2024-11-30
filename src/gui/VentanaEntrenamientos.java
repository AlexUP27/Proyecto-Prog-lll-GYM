package gui;

import javax.swing.*;
import java.awt.*;
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
            Arrays.asList("Estiramiento de Piernas", "Estiramiento de Hombros", "Estiramiento del Gato", "Estiramiento de Cuádriceps", "Estiramiento de Espalda Baja"),
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
	    detalles.put("Press de Banca", "1. Acuéstate en un banco con los pies firmemente apoyados en el suelo. \n2. Sostén la barra con las manos separadas un poco más del ancho de los hombros. \n3. Baja la barra lentamente hacia el pecho mientras mantienes los codos hacia afuera. \n4. Empuja la barra hacia arriba, extendiendo completamente los brazos. \n5. Mantén el control durante todo el movimiento");
	    detalles.put("Peso Muerto", "1. Coloca los pies debajo de la barra, separados al ancho de los hombros. \n2. Agarra la barra con ambas manos (agarre alterno o pronado). \n3. Mantén la espalda recta y el pecho levantado. \n4. Levanta la barra extendiendo las piernas y las caderas al mismo tiempo. \n5. Baja la barra controladamente manteniendo una buena postura");
	    detalles.put("Dominadas", "1. Agarra la barra con las manos separadas al ancho de los hombros. \n2. Mantén el cuerpo recto y los pies juntos. \n3. Tira del cuerpo hacia arriba hasta que la barbilla pase por encima de la barra. \n4. Baja lentamente a la posición inicial. \n5. Evita balancear el cuerpo para mantener la forma");
	    detalles.put("Remo con Barra", "1. Coloca los pies separados al ancho de los hombros. \n2. Agarra la barra con un agarre pronado o supinado, con las manos ligeramente más anchas que los hombros. \n3. Inclina el torso hacia adelante manteniendo la espalda recta. \n4. Lleva la barra hacia el abdomen contrayendo los músculos de la espalda. \n5. Baja la barra controladamente a la posición inicial");
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
        imagenes.put("Dominadas", "img/peso_muerto.jpeg"); //Cambiar foto
        imagenes.put("Remo con Mancuernas", "img/peso_muerto.jpeg"); //Cambiar foto
        return imagenes;
    }

    private Map<String, String> crearDetallesFlexibilidad() {
        Map<String, String> detalles = new HashMap<>();

        detalles.put("Estiramiento de Piernas", "1. Siéntate en el suelo con las piernas estiradas hacia adelante. \n2. Inclina el torso hacia adelante alcanzando los pies con las manos. \n3. Mantén la espalda recta mientras sientes el estiramiento en los isquiotibiales. \n4. Mantén esta posición durante 20-30 segundos.");
        detalles.put("Estiramiento de Hombros", "1. Cruza un brazo frente al cuerpo y agárralo con la mano opuesta. \n2. Empuja suavemente hacia el cuerpo hasta sentir un estiramiento en el hombro. \n3. Mantén esta posición durante 20-30 segundos y cambia de lado.");
        detalles.put("Estiramiento del Gato", "1. Colócate en posición de cuatro patas. \n2. Arquea la espalda hacia arriba como un gato estirándose. \n3. Baja la espalda hacia abajo mientras levantas la cabeza y el coxis. \n4. Repite el movimiento de forma controlada durante 10-15 repeticiones.");
        detalles.put("Estiramiento de Cuádriceps", "1. Párate derecho y flexiona una pierna hacia atrás agarrándote el pie con la mano. \n2. Tira suavemente del pie hacia los glúteos sintiendo el estiramiento en el cuádriceps. \n3. Mantén esta posición durante 20-30 segundos y cambia de pierna.");
        detalles.put("Estiramiento de Espalda Baja", "1. Acuéstate boca arriba con las piernas dobladas y los pies apoyados en el suelo. \n2. Lleva ambas rodillas hacia el pecho abrazándolas con las manos. \n3. Mantén esta posición durante 20-30 segundos. \n4. Relaja y repite si es necesario.");
        return detalles;
    }
    
    private Map<String, String> crearBrevesFlexibilidad() {
        Map<String, String> breves = new HashMap<>();
        breves.put("Estiramiento de Piernas", "Un clásico estiramiento para mejorar la flexibilidad de los isquiotibiales.");  
        breves.put("Estiramiento de Hombros", "Perfecto para liberar la tensión acumulada en los hombros.");
        breves.put("Estiramiento del Gato", "Un movimiento dinámico ideal para relajar la espalda y la columna vertebral.");
        breves.put("Estiramiento de Cuádriceps", "Sencillo y efectivo para relajar los músculos del muslo.");
        breves.put("Estiramiento de Espalda Baja", "Ayuda a aliviar la tensión en la zona lumbar de forma suave.");
        return breves;
    }


    private Map<String, String> crearImagenesFlexibilidad() {
        Map<String, String> imagenes = new HashMap<>();
        imagenes.put("Estiramiento de Piernas", "img/yoga.jpeg"); //Cambiar foto
        imagenes.put("Estiramiento de Hombros", "img/estiramiento.jpeg"); //Cambiar foto
        imagenes.put("Estiramiento del Gato", "img/puente.jpeg"); //Cambiar foto
        imagenes.put("Estiramiento de Cuádriceps", "img/puente.jpeg"); //Cambiar foto
        imagenes.put("Estiramiento de Espalda Baja", "img/puente.jpeg"); //Cambiar foto
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
