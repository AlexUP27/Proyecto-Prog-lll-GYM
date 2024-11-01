package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class HorarioClases extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaHorario;
    private DefaultTableModel modeloHorario;
    private JButton btnInscribirse, btnCerrar;

    // Lista para almacenar las celdas seleccionadas (día y hora)
    private ArrayList<int[]> celdasSeleccionadas;

    public HorarioClases() {
        setTitle("Horario de Clases");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Inicializar la lista de celdas seleccionadas
        celdasSeleccionadas = new ArrayList<>();

        // Crear paneles para organizar los componentes de la ventana
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelBotones = new JPanel(new BorderLayout());

        // Crear encabezados y datos de ejemplo para la tabla de horario
        String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        String[][] datos = {
            {"08:00 - 09:00", "Yoga", "Pilates", "Spinning", "Yoga", "HIIT"},
            {"09:00 - 10:00", "Cardio", "Yoga", "Spinning", "Zumba", "Boxeo"},
            {"10:00 - 11:00", "Crossfit", "Pilates", "Cardio", "Crossfit", "Zumba"},
            {"11:00 - 12:00", "Zumba", "HIIT", "Yoga", "Boxeo", "Spinning"},
            {"12:00 - 13:00", "Spinning", "Crossfit", "Pilates", "HIIT", "Yoga"},
            {"13:00 - 14:00", "Boxeo", "Zumba", "Crossfit", "Pilates", "Cardio"},
        };

        // Crear modelo y tabla para el horario
        modeloHorario = new DefaultTableModel(datos, columnas);
        tablaHorario = new JTable(modeloHorario) {
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que las celdas no sean editables
            }
        };

        tablaHorario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        tablaHorario.setRowHeight(30);

        // Añadir un renderizador de celda personalizado para cambiar el color de las celdas seleccionadas
        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Cambiar color de las celdas seleccionadas a amarillo
                if (isCeldaSeleccionada(row, column)) {
                    comp.setBackground(Color.YELLOW);
                } else {
                    comp.setBackground(Color.WHITE);
                }

                return comp;
            }
        });

        // Permitir solo selección de celdas individuales en la tabla
        tablaHorario.setCellSelectionEnabled(true);
        tablaHorario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Listener para manejar la selección de celdas y añadirlas a la lista
        tablaHorario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                int fila = tablaHorario.getSelectedRow();
                int columna = tablaHorario.getSelectedColumn();

                // Ignorar si la selección es inválida o si selecciona la columna de "Hora"
                if (fila >= 0 && columna > 0) {
                    agregarClaseSeleccionada(fila, columna);
                }
            }
        });

        // Crear el botón "Inscribirse en Clases Seleccionadas"
        btnInscribirse = new JButton("Inscribirse en Clases Seleccionadas");
        btnInscribirse.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        // Crear el botón "Cerrar" y agregarlo a la parte inferior derecha
        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        panelBotones.add(btnCerrar, BorderLayout.EAST);
        
        // Listener para el botón "Cerrar"
        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual de HorarioClases
            }
        });

        // Añadir el botón de inscripción al panel de botones en el centro
        panelBotones.add(btnInscribirse, BorderLayout.CENTER);

        // Listener para el botón de "Inscribirse"
        btnInscribirse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribirEnClases();
            }
        });

        // Añadir la tabla de horario y el panel de botones al panel principal
        panelPrincipal.add(tablaHorario.getTableHeader(), BorderLayout.NORTH);
        panelPrincipal.add(tablaHorario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        // Añadir el panel principal al JFrame
        add(panelPrincipal);
        setVisible(true);
    }

    // Método para agregar una clase a la lista de seleccionadas y actualizar la tabla
    private void agregarClaseSeleccionada(int fila, int columna) {
        // Verificar si la clase ya está seleccionada
        if (!isCeldaSeleccionada(fila, columna)) {
            celdasSeleccionadas.add(new int[]{fila, columna});
            tablaHorario.repaint(); // Refrescar la tabla para mostrar la selección
        } else {
            // Mensaje de advertencia si la clase ya está seleccionada
            JOptionPane.showMessageDialog(this, "La clase ha sido seleccionada.", "Clase Seleccionada", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para verificar si una celda está seleccionada
    private boolean isCeldaSeleccionada(int fila, int columna) {
        for (int[] celda : celdasSeleccionadas) {
            if (celda[0] == fila && celda[1] == columna) {
                return true;
            }
        }
        return false;
    }

    // Método para inscribir al usuario en las clases seleccionadas
    private void inscribirEnClases() {
        // Validar que haya celdas seleccionadas
        if (celdasSeleccionadas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una o más clases para inscribirse.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mensaje de confirmación para cada clase seleccionada
        StringBuilder mensaje = new StringBuilder("Inscripción exitosa en las siguientes clases:\n");
        for (int[] celda : celdasSeleccionadas) {
            String clase = (String) modeloHorario.getValueAt(celda[0], celda[1]);
            String dia = tablaHorario.getColumnName(celda[1]);
            String hora = (String) modeloHorario.getValueAt(celda[0], 0);
            mensaje.append("- ").append(clase).append(" el ").append(dia).append(" a las ").append(hora).append("\n");
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Confirmación de Inscripción", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar la lista de clases seleccionadas y actualizar la tabla
        celdasSeleccionadas.clear();
        tablaHorario.repaint();
    }

}
