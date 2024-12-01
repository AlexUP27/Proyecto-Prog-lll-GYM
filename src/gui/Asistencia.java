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

        // Inicializar la animación (ventana secundaria)
        mostrarAnimacion();

        // Inicialmente la ventana no se hace visible hasta que termine la carga
        setVisible(false);
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

    /**
     * Muestra la animación antes de cargar la ventana principal.
     */
    private void mostrarAnimacion() {
        // Crear una ventana secundaria (JWindow)
        JWindow ventanaAnimacion = new JWindow();
        ventanaAnimacion.setSize(400, 200);
        ventanaAnimacion.setLocationRelativeTo(null);

        // Panel para mostrar la animación o mensaje
        JPanel panelAnimacion = new JPanel();
        panelAnimacion.setLayout(new BorderLayout());
        JLabel lblCargando = new JLabel("Cargando datos...", JLabel.CENTER);
        lblCargando.setFont(new Font("Arial", Font.BOLD, 20));
        panelAnimacion.add(lblCargando, BorderLayout.CENTER);

        ventanaAnimacion.add(panelAnimacion);
        ventanaAnimacion.setVisible(true);

        // Hilo para cambiar el texto de la animación antes de mostrar la ventana
        new Thread(() -> {
            try {
                String[] mensajes = {
                        "Cargando datos...",
                        "Preparando la información...",
                        "Cargando asistencias...",
                        "Casi listo, un momento...",
                        "Listo"
                };

                for (String mensaje : mensajes) {
                    SwingUtilities.invokeLater(() -> lblCargando.setText(mensaje));
                    Thread.sleep(1000);  // Cambia el mensaje cada 1 segundo
                }

                // Simula la carga de datos
                Thread.sleep(2000);  // Simula un retraso en la carga de los datos cada 2 segundos

                // Cargar los datos de las asistencias
                if (!cargarAsistencias()) {
                    SwingUtilities.invokeLater(this::inicializarDatosPredeterminados);
                }

                // Al finalizar la carga, mostrar la ventana principal
                SwingUtilities.invokeLater(() -> {
                    ventanaAnimacion.setVisible(false); // Ocultar la animación
                    setVisible(true); // Hacer visible la ventana de asistencia
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Inicialmente la ventana no se hace visible hasta que termine la carga
        setVisible(false);
    }
}