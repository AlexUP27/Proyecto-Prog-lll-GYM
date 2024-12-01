package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HorarioClases extends JFrame {
    private static final long serialVersionUID = 1L;
    private ModeloHorario modeloHorario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public HorarioClases(ModeloHorario modeloHorario) {
        this.modeloHorario = modeloHorario;

        setTitle("Horario de Clases");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        modeloTabla = new DefaultTableModel(modeloHorario.getHorarios(), columnas);
        tabla = new JTable(modeloTabla);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 10, 10));

        JButton btnModificar = new JButton("Modificar");
        JButton btnInsertar = new JButton("Insertar");
        JButton btnEliminar = new JButton("Eliminar");

        // Usando lambdas en lugar de ActionListener anónimo
        btnModificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            int columna = tabla.getSelectedColumn();
            if (fila >= 0 && columna >= 0) {
                String nuevoValor = JOptionPane.showInputDialog(
                        HorarioClases.this,
                        "Nuevo valor para la clase:",
                        tabla.getValueAt(fila, columna)
                );
                if (nuevoValor != null && !nuevoValor.trim().isEmpty()) {
                    modeloHorario.modificarClase(fila, columna, nuevoValor);
                    actualizarTabla();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una celda.");
            }
        });

        btnInsertar.addActionListener(e -> {
            String nuevaHora = JOptionPane.showInputDialog("Nueva hora (ej. 22:00 - 23:00):");
            if (nuevaHora != null && !nuevaHora.trim().isEmpty()) {
                String[] nuevaFila = new String[6];
                nuevaFila[0] = nuevaHora;
                for (int i = 1; i < nuevaFila.length; i++) {
                    nuevaFila[i] = JOptionPane.showInputDialog("Clase para " + columnas[i] + ":");
                }
                modeloHorario.insertarClase(-1, nuevaFila);
                actualizarTabla();
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();
            int columna = tabla.getSelectedColumn();
            if (fila >= 0 && columna >= 0) {
                modeloHorario.eliminarClase(fila, columna);
                actualizarTabla();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una celda.");
            }
        });

        panelBotones.add(btnModificar);
        panelBotones.add(btnInsertar);
        panelBotones.add(btnEliminar);

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void actualizarTabla() {
        String[][] nuevosDatos = modeloHorario.getHorarios();
        modeloTabla.setDataVector(nuevosDatos, new String[]{"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"});
    }
}
