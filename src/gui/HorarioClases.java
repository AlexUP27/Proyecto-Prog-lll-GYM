package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class HorarioClases extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> clasesSeleccionadas;
    private JButton btnVerInscripciones, btnCerrar;
    private JTable tablaHorario;

    public HorarioClases() {
        clasesSeleccionadas = new ArrayList<>();

        setTitle("Horario de Clases");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelTabla = crearPanelTablaHorario();
        add(panelTabla, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnVerInscripciones = new JButton("Ver Inscripciones");
        btnVerInscripciones.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        panelBotones.add(btnVerInscripciones);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnVerInscripciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentanaInscripciones();
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private JPanel crearPanelTablaHorario() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnas = {"Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
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
            {"21:00 - 22:00", "HIIT", "Stretching", "Boxeo", "Cardio", "Body Pump"},
        };

        DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Las celdas no son editables
            }
        };

        tablaHorario = new JTable(modeloTabla);
        tablaHorario.setCellSelectionEnabled(true);
        tablaHorario.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        // Cambia el color de las clases seleccionadas
        tablaHorario.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // No permitir seleccionar clases de descanso
                if (value.equals("Descanso")) {
                    comp.setBackground(Color.LIGHT_GRAY);
                } else if (clasesSeleccionadas.contains(value + " - " + table.getColumnName(column) + " a las " + table.getValueAt(row, 0))) {
                    comp.setBackground(Color.GREEN);
                } else {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }
        });

        tablaHorario.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int[] filasSeleccionadas = tablaHorario.getSelectedRows();
                int[] columnasSeleccionadas = tablaHorario.getSelectedColumns();

                for (int fila : filasSeleccionadas) {
                    for (int columna : columnasSeleccionadas) {
                        if (columna > 0) {
                            String clase = (String) modeloTabla.getValueAt(fila, columna);
                            String hora = (String) modeloTabla.getValueAt(fila, 0);
                            String dia = modeloTabla.getColumnName(columna);
                            String claseCompleta = clase + " - " + dia + " a las " + hora;
                            // No permitir seleccionar clase de descanso
                            if (!clase.equals("Descanso")) {
                                if (clasesSeleccionadas.contains(claseCompleta)) {
                                    clasesSeleccionadas.remove(claseCompleta);
                                } else {
                                    clasesSeleccionadas.add(claseCompleta);
                                }
                            }
                        }
                    }
                }
                tablaHorario.repaint();
            }
        });

        JScrollPane scrollTabla = new JScrollPane(tablaHorario);
        panel.add(scrollTabla, BorderLayout.CENTER);

        return panel;
    }

    private void mostrarVentanaInscripciones() {
        JFrame ventanaInscripciones = new JFrame("Clases Inscritas");
        ventanaInscripciones.setSize(300, 200);
        ventanaInscripciones.setLocationRelativeTo(null);

        JPanel panelInscripciones = new JPanel();
        panelInscripciones.setLayout(new BoxLayout(panelInscripciones, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panelInscripciones);
        scrollPane.setPreferredSize(new Dimension(280, 150));

        if (clasesSeleccionadas.isEmpty()) {
            panelInscripciones.add(new JLabel("No estás inscrito en ninguna clase."));
        } else {
            for (String clase : clasesSeleccionadas) {
                panelInscripciones.add(new JLabel(clase));
            }
        }

        ventanaInscripciones.add(scrollPane, BorderLayout.CENTER);

        JButton btnCerrarVentana = new JButton("Cerrar");
        btnCerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaInscripciones.dispose();
            }
        });
        ventanaInscripciones.add(btnCerrarVentana, BorderLayout.SOUTH);

        ventanaInscripciones.setVisible(true);
    }
}
