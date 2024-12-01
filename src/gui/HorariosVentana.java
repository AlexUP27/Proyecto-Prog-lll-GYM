package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HorariosVentana extends JFrame {
    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelo;
    private JTable tabla;

    // Constructor de la ventana de horarios
    public HorariosVentana() {
        // Configuración de la ventana
        setTitle("Horario de Clases");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Solo cierra esta ventana, no la aplicación
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // Confirmación o lógica adicional antes de cerrar si lo deseas
                dispose(); // Cierra solo la ventana de horarios
            }
        });

        // Definir las columnas y los datos del horario
        String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        modelo = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modelo);

        // Cargar los horarios predeterminados
        cargarHorarioPredeterminado();

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 10));

        // Botones de la ventana
        JButton btnModificar = new JButton("Modificar");
        JButton btnInsertar = new JButton("Insertar");
        JButton btnEliminar = new JButton("Eliminar");

        // Acción para el botón Modificar
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                int columna = tabla.getSelectedColumn();
                if (fila >= 0 && columna >= 0) {
                    String nuevoValor = JOptionPane.showInputDialog(
                            HorariosVentana.this,
                            "Nuevo valor para la clase:",
                            tabla.getValueAt(fila, columna)
                    );
                    if (nuevoValor != null && !nuevoValor.trim().isEmpty()) {
                        tabla.setValueAt(nuevoValor, fila, columna);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una celda.");
                }
            }
        });

        // Acción para el botón Insertar
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevaHora = JOptionPane.showInputDialog("Nueva hora (ej. 22:00 - 23:00):");
                if (nuevaHora != null && !nuevaHora.trim().isEmpty()) {
                    String[] nuevaFila = new String[6]; // 6 columnas, incluyendo viernes
                    nuevaFila[0] = nuevaHora; // La primera columna es la hora
                    for (int i = 1; i < nuevaFila.length; i++) {
                        // Preguntar por la clase para cada día, incluyendo el viernes
                        nuevaFila[i] = JOptionPane.showInputDialog("Clase para " + columnas[i] + ":");
                    }
                    modelo.addRow(nuevaFila); // Agregar la nueva fila a la tabla
                }
            }
        });

        // Acción para el botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = tabla.getSelectedRow();
                int columna = tabla.getSelectedColumn();
                if (fila >= 0 && columna >= 0) {
                    // Confirmar si el usuario desea eliminar el valor
                    int confirmacion = JOptionPane.showConfirmDialog(
                            HorariosVentana.this,
                            "¿Está seguro que desea eliminar este horario?",
                            "Confirmar Eliminación",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Eliminar el valor de la celda seleccionada
                        modelo.setValueAt("", fila, columna);  // Establece la celda vacía
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una celda para eliminar.");
                }
            }
        });

        // Añadir botones al panel
        panelBotones.add(btnModificar);
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);

        // Añadir los componentes a la ventana
        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Método para cargar los horarios predeterminados
    private void cargarHorarioPredeterminado() {
        String[][] datos = {
            {"08:00 - 09:00", "Yoga", "Pilates", "Spinning", "Body Pump", "HIIT"},
            {"09:00 - 10:00", "Cardio", "Yoga", "TRX", "Zumba", "Boxeo"},
            {"10:00 - 11:00", "Crossfit", "Body Pump", "Cardio", "Crossfit", "Stretching"},
            {"11:00 - 12:00", "Zumba", "HIIT", "Power Yoga", "Boxeo", "Spinning"},
            {"12:00 - 13:00", "Spinning", "Crossfit", "TRX", "HIIT", "Body Pump"},
            {"13:00 - 14:00", "Boxeo", "Zumba", "Stretching", "Pilates", "Cardio"},
            {"14:00 - 15:00", "Descanso", "Descanso", "Descanso", "Descanso", "Descanso"},
            {"15:00 - 16:00", "Yoga", "Body Pump", "Spinning", "TRX", "HIIT"},
            {"16:00 - 17:00", "Cardio", "Yoga", "Spinning", "Zumba", "Body Combat"},
            {"17:00 - 18:00", "Crossfit", "TRX", "Cardio", "Crossfit", "Stretching"},
            {"18:00 - 19:00", "Body Combat", "HIIT", "Yoga", "Boxeo", "Spinning"},
            {"19:00 - 20:00", "Power Yoga", "Crossfit", "Pilates", "HIIT", "TRX"},
            {"20:00 - 21:00", "Boxeo", "Zumba", "Crossfit", "Pilates", "Cardio"},
            {"21:00 - 22:00", "HIIT", "Stretching", "Boxeo", "Cardio", "Body Pump"}
        };
        
        for (String[] fila : datos) {
            modelo.addRow(fila);  // Agregar cada fila predeterminada a la tabla
        }
    }
}