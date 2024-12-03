package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaRutinas extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaRutinas() {
        setTitle("Rutinas Personalizadas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Contenido de la ventana de Rutinas
        JLabel label = new JLabel("Aquí se gestionarán las rutinas de los clientes.");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
