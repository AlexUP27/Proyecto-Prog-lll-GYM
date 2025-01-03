package gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSesionSemanal extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaSesionSemanal() {
        setTitle("Progreso de Clientes");
        setBounds(300, 200, 1280, 500); //1280 porque cada foto mide 256x256 y para que entren justas
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        List<String> sesionSemanal = generarSesionAleatoria();

        // Configuración del GridLayout
        JPanel panel = new JPanel(new GridLayout(1, 5));

        // Añadir entrenamientos al panel
        int dia = 0;
        for (String entrenamiento : sesionSemanal) {
            JPanel entrenamientosPanel = new JPanel(new GridLayout(2, 1));

            // Añadir imagen del entrenamiento
            JLabel imagen = new JLabel();
            ImageIcon icono = new ImageIcon("img/" + entrenamiento + ".png");
            imagen.setIcon(icono);
            imagen.setHorizontalAlignment(JLabel.CENTER);
            entrenamientosPanel.add(imagen);
            
            if (dia == 0) {
                JLabel nombreLabel = new JLabel("Lunes:"+entrenamiento);
                nombreLabel.setHorizontalAlignment(JLabel.CENTER);
                entrenamientosPanel.add(nombreLabel);

                panel.add(entrenamientosPanel);
                }
            else if(dia == 1) {
                JLabel nombreLabel = new JLabel("Martes:"+entrenamiento);
                nombreLabel.setHorizontalAlignment(JLabel.CENTER);
                entrenamientosPanel.add(nombreLabel);

                panel.add(entrenamientosPanel);
                }
            else if (dia == 2) {
                JLabel nombreLabel = new JLabel("Miercoles:"+entrenamiento);
                nombreLabel.setHorizontalAlignment(JLabel.CENTER);
                entrenamientosPanel.add(nombreLabel);

                panel.add(entrenamientosPanel);
                }
            else if (dia == 3) {
                JLabel nombreLabel = new JLabel("Jueves:"+entrenamiento);
                nombreLabel.setHorizontalAlignment(JLabel.CENTER);
                entrenamientosPanel.add(nombreLabel);

                panel.add(entrenamientosPanel);
                }
            else {
                JLabel nombreLabel = new JLabel("Viernes:"+entrenamiento);
                nombreLabel.setHorizontalAlignment(JLabel.CENTER);
                entrenamientosPanel.add(nombreLabel);

                panel.add(entrenamientosPanel);
                }
            dia++;
        }

        add(panel);
        setVisible(true);
    }

    public List<String> generarSesionSemanal(List<List<String>> entrenamientos) {
        return generarSesionRecursiva(entrenamientos, entrenamientos.size() - 1, new ArrayList<>());
    }

    private List<String> generarSesionRecursiva(List<List<String>> entrenamientos, int diasRestantes, List<String> sesionActual) {
        if (diasRestantes == 0) {
            return sesionActual;
        }

        List<String> diaActualEntrenamientos = entrenamientos.get(entrenamientos.size() - diasRestantes);
        String entrenamientoAleatorio = diaActualEntrenamientos.get(new Random().nextInt(diaActualEntrenamientos.size()));

        sesionActual.add(entrenamientoAleatorio);

        return generarSesionRecursiva(entrenamientos, diasRestantes - 1, sesionActual);
    }

    public List<List<String>> generarEntrenamientos() {
        List<List<String>> entrenamientosSemanales = new ArrayList<>();
        entrenamientosSemanales.add(List.of("Yoga", "Pilates", "Spinning", "Body Pump", "HIIT"));
        entrenamientosSemanales.add(List.of("Cardio", "Yoga", "TRX", "Zumba", "Boxeo"));
        entrenamientosSemanales.add(List.of("Crossfit", "Body Pump", "Cardio", "Crossfit", "Stretching"));
        entrenamientosSemanales.add(List.of("Zumba", "HIIT", "Power Yoga", "Body Pump", "Spinning"));
        entrenamientosSemanales.add(List.of("Spinning", "Crossfit", "TRX", "HIIT", "Body Pump"));
        entrenamientosSemanales.add(List.of("Boxeo", "Zumba", "Stretching", "Pilates", "Cardio"));

        return entrenamientosSemanales;
    }

    public List<String> generarSesionAleatoria() {
        List<List<String>> entrenamientosSemanales = generarEntrenamientos();
        return generarSesionSemanal(entrenamientosSemanales);
    }
    
}
