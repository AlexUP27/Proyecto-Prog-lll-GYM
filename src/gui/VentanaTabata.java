package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaTabata extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEmpezar;
    private JButton btnDetener;
    private Timer timer;
    private int tiempoRestante; 
    private boolean enDescanso = false;
    private JLabel lblTiempo;
    private int contadorRondas = 0;  // Contador de rondas

    public VentanaTabata() {
        // Configuracion de la ventana
        setTitle("Ventana Tabata");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuracion del panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear un panel para el contador de la cuenta atras con borde
        JPanel panelContador = new JPanel();
        panelContador.setBackground(Color.WHITE);
        panelContador.setPreferredSize(new Dimension(200, 200));
        panelContador.setLayout(new BorderLayout());
        panelContador.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Etiqueta para mostrar el tiempo (mas pequeño)
        lblTiempo = new JLabel("Preparado para empezar el entrenamiento", JLabel.CENTER);
        lblTiempo.setFont(new Font("Arial", Font.BOLD, 20));  // Fuente mas pequeña
        panelContador.add(lblTiempo, BorderLayout.CENTER);

        // Añadir el panel de contador al panel principal
        panel.add(panelContador, BorderLayout.CENTER);

        // Etiqueta para el mensaje superior
        JLabel lblMensaje = new JLabel("Usa el Tabata para medir tus entrenamientos", JLabel.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblMensaje, BorderLayout.NORTH);

        // Etiqueta para mostrar el contador de rondas
        JLabel lblRondas = new JLabel("Rondas: 0", JLabel.CENTER);
        lblRondas.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblRondas, BorderLayout.NORTH);

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // Inicialización del boton "Empezar"
        btnEmpezar = new JButton("Empezar");
        btnEmpezar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnEmpezar.setBackground(Color.GREEN);
        btnEmpezar.setForeground(Color.WHITE);
        btnEmpezar.setFocusPainted(false); // Sin borde al seleccionar
        panelBotones.add(btnEmpezar);

        // Inicialización del boton "Detener"
        btnDetener = new JButton("Detener");
        btnDetener.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnDetener.setBackground(Color.RED);
        btnDetener.setForeground(Color.WHITE);
        btnDetener.setFocusPainted(false); // Sin borde al seleccionar
        panelBotones.add(btnDetener);

        // Deshabilitar el boton "Detener" inicialmente
        btnDetener.setEnabled(false);

        // Añadir los paneles al marco
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);

        // Hacer visible la ventana
        setVisible(true);

        // Listener para el boton "Empezar"
        btnEmpezar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tiempoRestante = 20;  // Tiempo de trabajo en segundos
                enDescanso = false;   // Comienza con el entrenamiento
                lblTiempo.setText("Entrenamiento en curso... Tiempo restante: " + tiempoRestante + " segundos.");
                btnEmpezar.setEnabled(false);
                btnDetener.setEnabled(true);

                // Crear un temporizador
                timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tiempoRestante > 0 && !enDescanso) {
                            tiempoRestante--;
                            lblTiempo.setText("Entrenamiento... Tiempo restante: " + tiempoRestante + " segundos.");
                        } else if (tiempoRestante == 0 && !enDescanso) {
                            // Si termina el entrenamiento cambia al periodo de descanso
                            enDescanso = true;
                            tiempoRestante = 10;  // Tiempo de descanso
                            lblTiempo.setText("Descanso... Tiempo restante: " + tiempoRestante + " segundos.");
                        } else if (tiempoRestante > 0 && enDescanso) {
                            // Si esta en descanso descontamos el tiempo
                            tiempoRestante--;
                            lblTiempo.setText("Descanso... Tiempo restante: " + tiempoRestante + " segundos.");
                        } else if (tiempoRestante == 0 && enDescanso) {
                            // Si termina el descanso, incrementamos el contador de rondas
                            contadorRondas++;
                            lblTiempo.setText("Entrenamiento completado. ¡Buen trabajo!");
                            lblRondas.setText("Rondas: " + contadorRondas);  // Actualizar el contador de rondas
                            timer.stop();
                            btnEmpezar.setEnabled(true);
                            btnDetener.setEnabled(false);
                        }
                    }
                });
                timer.start();
            }
        });

        // Listener para el boton "Detener"
        btnDetener.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (timer != null) {
                    timer.stop();
                }
                lblTiempo.setText("Entrenamiento detenido.");
                btnEmpezar.setEnabled(true);
                btnDetener.setEnabled(false);
            }
        });
    }
}

