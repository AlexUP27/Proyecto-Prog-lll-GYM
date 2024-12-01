package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaNotificaciones extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaNotificaciones() {
        setTitle("Notificaciones");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Contenido de la ventana de Notificaciones
        JLabel label = new JLabel("Aquí se gestionarán las notificaciones.");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
