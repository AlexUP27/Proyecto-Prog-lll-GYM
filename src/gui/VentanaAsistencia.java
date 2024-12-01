package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaAsistencia extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaAsistencia() {
        setTitle("Asistencia de Clientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Contenido de la ventana de Asistencia
        JLabel label = new JLabel("Aquí se gestionará la asistencia de los clientes.");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
