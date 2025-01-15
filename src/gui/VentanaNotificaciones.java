package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaNotificaciones extends JFrame {
    private static final long serialVersionUID = 1L;

    private DefaultListModel<String> notificacionesModel;
    private JList<String> notificacionesList;

    public VentanaNotificaciones() {
        setTitle("Notificaciones");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Configuración del layout
        setLayout(new BorderLayout());

        // Crear el modelo para la lista de notificaciones
        notificacionesModel = new DefaultListModel<>();
        notificacionesList = new JList<>(notificacionesModel);
        JScrollPane scrollPane = new JScrollPane(notificacionesList);
        add(scrollPane, BorderLayout.CENTER);

        // Crear panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botón para agregar una notificación
        JButton btnAgregar = new JButton("Agregar Notificación");
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String notificacion = JOptionPane.showInputDialog("Ingresa la notificación:");
                if (notificacion != null && !notificacion.isEmpty()) {
                    notificacionesModel.addElement(notificacion);
                }
            }
        });

        // Botón para eliminar la notificación seleccionada
        JButton btnEliminar = new JButton("Eliminar Notificación");
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = notificacionesList.getSelectedIndex();
                if (selectedIndex != -1) {
                    notificacionesModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecciona una notificación para eliminar.");
                }
            }
        });

        // Agregar botones al panel
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnEliminar);

        // Agregar panel de botones a la ventana
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
