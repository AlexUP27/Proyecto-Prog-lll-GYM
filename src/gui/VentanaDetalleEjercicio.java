package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaDetalleEjercicio extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaDetalleEjercicio(String ejercicio, String detalle) {
        super("Detalles de: " + ejercicio);

        // Configuración básica
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear un área de texto para mostrar los detalles
        JTextArea areaDetalles = new JTextArea(detalle);
        areaDetalles.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        areaDetalles.setEditable(false);
        areaDetalles.setWrapStyleWord(true);
        areaDetalles.setLineWrap(true);

        // Añadir el área de texto a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(areaDetalles);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cómo realizar este ejercicio"));

        // Añadir el JScrollPane a la ventana
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }
}


