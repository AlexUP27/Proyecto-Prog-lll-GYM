package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class VentanaEjercicios extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, String> ejerciciosDetalles;
    private Map<String, String> ejerciciosImagenes;
    private Map<String, String> ejerciciosBreves;
    private JLabel lblImagenEjercicio;
    private JLabel lblDescripcionCorta;

    public VentanaEjercicios(String tipoEntrenamiento, List<String> ejercicios, Map<String, String> detalles, Map<String, String> imagenes, Map<String, String> breves) {
        super("Ejercicios de " + tipoEntrenamiento);

        // Configuración básica de la ventana
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Almacenar detalles, rutas de imágenes y descripciones breves
        this.ejerciciosDetalles = detalles;
        this.ejerciciosImagenes = imagenes;
        this.ejerciciosBreves = breves;

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lista de ejercicios
        JList<String> listaEjercicios = new JList<>(ejercicios.toArray(new String[0]));
        listaEjercicios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaEjercicios);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Ejercicios"));

        // Imagen y descripción corta del ejercicio
        lblImagenEjercicio = new JLabel();
        lblImagenEjercicio.setHorizontalAlignment(JLabel.CENTER);
        lblImagenEjercicio.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblImagenEjercicio.setPreferredSize(new Dimension(200, 150));

        lblDescripcionCorta = new JLabel("", SwingConstants.CENTER);
        lblDescripcionCorta.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 12));
        lblDescripcionCorta.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Panel para mostrar detalles
        JPanel panelDetalles = new JPanel(new BorderLayout());
        panelDetalles.add(lblImagenEjercicio, BorderLayout.NORTH);
        panelDetalles.add(lblDescripcionCorta, BorderLayout.CENTER);

        // Botón para ver detalles
        JButton btnVerDetalles = new JButton("Ver Detalles");
        btnVerDetalles.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        panelDetalles.add(btnVerDetalles, BorderLayout.SOUTH);

        // Panel central
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, panelDetalles);
        splitPane.setDividerLocation(200);

        // Añadir todo al panel principal
        panelPrincipal.add(splitPane, BorderLayout.CENTER);

        // Añadir el panel a la ventana
        add(panelPrincipal);

        // Listener para actualizar la imagen y descripción breve al seleccionar un ejercicio
        listaEjercicios.addListSelectionListener(e -> {
            String ejercicioSeleccionado = listaEjercicios.getSelectedValue();
            if (ejercicioSeleccionado != null) {
                actualizarVistaEjercicio(ejercicioSeleccionado);
            }
        });

        // Acción del botón "Ver Detalles"
        btnVerDetalles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ejercicioSeleccionado = listaEjercicios.getSelectedValue();
                if (ejercicioSeleccionado != null) {
                    String detalle = ejerciciosDetalles.get(ejercicioSeleccionado);
                    new VentanaDetalleEjercicio(ejercicioSeleccionado, detalle);
                } else {
                    JOptionPane.showMessageDialog(
                        VentanaEjercicios.this,
                        "Por favor, selecciona un ejercicio.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        setVisible(true);
    }

    private void actualizarVistaEjercicio(String ejercicio) {
        String rutaImagen = ejerciciosImagenes.get(ejercicio);
        String descripcionCorta = ejerciciosBreves.get(ejercicio);

        if (rutaImagen != null) {
            ImageIcon icono = new ImageIcon(rutaImagen);
            Image imagenRedimensionada = icono.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            lblImagenEjercicio.setIcon(new ImageIcon(imagenRedimensionada));
        } else {
            lblImagenEjercicio.setIcon(null);
        }

        lblDescripcionCorta.setText(descripcionCorta != null ? descripcionCorta : "");
    }
}

