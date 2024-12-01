package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeguimientoProgreso extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tablaProgreso;
    private DefaultTableModel modeloTabla;
    private JTextField txtFecha, txtPeso, txtMedidas, txtDuracion;
    private final String ARCHIVO_DATOS = "progreso.txt";

    public SeguimientoProgreso() {
        super("Seguimiento de Progreso");

        // Configuración básica
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

        // Panel para ingresar datos
        JPanel panelIngreso = new JPanel(new GridLayout(5, 2, 10, 10));
        panelIngreso.setBorder(BorderFactory.createTitledBorder("Registrar Progreso"));

        // Campos de entrada
        panelIngreso.add(new JLabel("Fecha (dd/MM/yyyy):"));
        txtFecha = new JTextField();
        panelIngreso.add(txtFecha);

        panelIngreso.add(new JLabel("Peso (kg):"));
        txtPeso = new JTextField();
        panelIngreso.add(txtPeso);

        panelIngreso.add(new JLabel("Medidas Corporales (cm):"));
        txtMedidas = new JTextField();
        panelIngreso.add(txtMedidas);

        panelIngreso.add(new JLabel("Duración del Ejercicio (min):"));
        txtDuracion = new JTextField();
        panelIngreso.add(txtDuracion);

        // Botón para agregar progreso
        JButton btnAgregar = new JButton("Añadir Progreso");
        panelIngreso.add(btnAgregar);

        // Tabla para mostrar el historial
        modeloTabla = new DefaultTableModel(new String[]{"Fecha", "Peso (kg)", "Medidas (cm)", "Duración (min)"}, 0);
        tablaProgreso = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaProgreso);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Historial de Progreso"));

        // Añadir componentes al panel principal
        panelPrincipal.add(panelIngreso, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        // Añadir el panel principal al JFrame
        add(panelPrincipal);

        // Cargar datos desde el archivo
        cargarDatos();

        // Acción del botón "Añadir Progreso"
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProgreso();
            }
        });

        // Mostrar la ventana
        setVisible(true);
    }

    private void agregarProgreso() {
        String fecha = txtFecha.getText();
        String peso = txtPeso.getText();
        String medidas = txtMedidas.getText();
        String duracion = txtDuracion.getText();

        // Validación de campos
        if (!esFechaValida(fecha)) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce una fecha válida (dd/MM/yyyy).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!esNumeroValido(peso) || !esNumeroValido(medidas) || !esNumeroValido(duracion)) {
            JOptionPane.showMessageDialog(this, "Peso, medidas y duración deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Agregar los datos a la tabla
        modeloTabla.addRow(new Object[]{fecha, peso, medidas, duracion});

        // Guardar los datos en el archivo
        guardarDatos();

        // Limpiar los campos de entrada
        txtFecha.setText("");
        txtPeso.setText("");
        txtMedidas.setText("");
        txtDuracion.setText("");
    }

    private boolean esFechaValida(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        try {
            Date fechaParseada = formatoFecha.parse(fecha);
            return fechaParseada.before(new Date()); // Solo permitir fechas pasadas o actuales
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean esNumeroValido(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void guardarDatos() {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARCHIVO_DATOS))) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String linea = modeloTabla.getValueAt(i, 0) + "," +
                        modeloTabla.getValueAt(i, 1) + "," +
                        modeloTabla.getValueAt(i, 2) + "," +
                        modeloTabla.getValueAt(i, 3);
                escritor.write(linea);
                escritor.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() {
        File archivo = new File(ARCHIVO_DATOS);
        if (!archivo.exists()) {
            return; // Si no hay archivo, no hay nada que cargar
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(ARCHIVO_DATOS))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                modeloTabla.addRow(datos);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}