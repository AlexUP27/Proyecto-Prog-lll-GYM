package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaProgresoClientes extends JFrame {
    private static final long serialVersionUID = 1L;

    public VentanaProgresoClientes() {
        setTitle("Progreso de Clientes");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Contenido de la ventana de Progreso de Clientes
        JLabel label = new JLabel("Aquí se mostrará el progreso de los clientes.");
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label, BorderLayout.CENTER);

        setVisible(true);
    }
}
