package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Asistencia extends JFrame {
    private static final long serialVersionUID = 1L;
    private final String archivoAsistencia = "asistencias.csv"; // Archivo para guardar datos
    private DefaultTableModel modeloTabla;

    public Asistencia() {
        setTitle("Asistencia");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Crear una tabla de asistencia
        String[] columnas = {"Nombre del Cliente", "Fecha", "Asistencia"};
        modeloTabla = new DefaultTableModel(columnas, 0); // Modelo de la tabla
        JTable tablaAsistencia = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaAsistencia);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Cargar los datos desde el archivo o inicializar con predeterminados
        if (!cargarAsistencias()) {
            inicializarDatosPredeterminados();
        }

        // Botón para registrar nueva asistencia
        JButton btnRegistrar = new JButton("Registrar Asistencia");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarNuevaAsistencia();
            }
        });

        panelPrincipal.add(btnRegistrar, BorderLayout.SOUTH);

        // Añadir el panel principal al frame
        add(panelPrincipal);

        setVisible(true);
    }

    /**
     * Registra una nueva asistencia en la tabla y la guarda en el archivo.
     */
    private void registrarNuevaAsistencia() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del Cliente:");
        String fecha = JOptionPane.showInputDialog(this, "Fecha (YYYY/MM/DD):");
        String asistencia = JOptionPane.showInputDialog(this, "Asistencia (Presente/Ausente):");

        if (nombre != null && fecha != null && asistencia != null) {
            modeloTabla.addRow(new Object[]{nombre, fecha, asistencia});
            guardarAsistencias();
        }
    }

    /**
     * Guarda las asistencias actuales de la tabla en un archivo CSV.
     */
    private void guardarAsistencias() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoAsistencia))) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                StringBuilder linea = new StringBuilder();
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    linea.append(modeloTabla.getValueAt(i, j));
                    if (j < modeloTabla.getColumnCount() - 1) {
                        linea.append(",");
                    }
                }
                writer.println(linea.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar las asistencias: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carga las asistencias desde un archivo CSV.
     * 
     * @return true si se cargaron datos del archivo, false si no se cargaron datos
     */
    private boolean cargarAsistencias() {
        File archivo = new File(archivoAsistencia);
        if (archivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                String linea;
                boolean hayDatos = false;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 3) {
                        modeloTabla.addRow(datos);
                        hayDatos = true;
                    }
                }
                return hayDatos;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar las asistencias: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }

    /**
     * Inicializa la tabla con datos predeterminados.
     */
    private void inicializarDatosPredeterminados() {
        String[][] datosPredeterminados = {
                {"Juan Pérez", "2024/12/01", "Presente"},
                {"María López", "2024/12/01", "Ausente"},
                {"Carlos García", "2024/12/01", "Presente"}
        };

        for (String[] fila : datosPredeterminados) {
            modeloTabla.addRow(fila);
        }

        // Guardar los datos predeterminados en el archivo
        guardarAsistencias();
    }
}
