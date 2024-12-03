package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaDetalleEntrenamiento extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VentanaDetalleEntrenamiento(String titulo, String descripcion) {
        super(titulo);

        // Configuración básica de la ventana
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta de título
        JLabel lblTitulo = new JLabel(titulo, JLabel.CENTER);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Etiqueta de descripción
        JTextArea areaDescripcion = new JTextArea(descripcion);
        areaDescripcion.setWrapStyleWord(true);
        areaDescripcion.setLineWrap(true);
        areaDescripcion.setEditable(false);
        areaDescripcion.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.add(new JScrollPane(areaDescripcion), BorderLayout.CENTER);

        // Añadir el panel a la ventana
        add(panelPrincipal);
        setVisible(true);
    }
}
