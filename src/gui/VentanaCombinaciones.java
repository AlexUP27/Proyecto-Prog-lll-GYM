package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VentanaCombinaciones extends JFrame {

    private static final long serialVersionUID = 1L;
    public static List<String> entrenamientos = Arrays.asList("Yoga", "Pilates", "Spinning", "Body Pump", "HIIT", "Power Yoga",
            "Cardio", "TRX", "Zumba", "Boxeo", "Crossfit", "Stretching", "Body Combat");

    public VentanaCombinaciones(String entrenamientoLunes) {

        setTitle("Ventana de Combinaciones");
        setBounds(300, 400, 500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = { "Numero Combinacion", "Lunes", "Martes", "Miercoles",
                "Jueves", "Viernes" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        
        // Alinear la primera columna al centro
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // Obtener todas las combinaciones que empiezan con "Yoga"
        List<List<String>> resultadoC = combinacionesSinRepeticion(entrenamientos, entrenamientoLunes);

        // Agregar las combinaciones a la tabla
        int index = 1;
        for (List<String> combinacion : resultadoC) {
            Object[] row = new Object[6]; // 1 columna para el índice, 5 para los entrenamientos
            row[0] = index++; // Número de combinación
            for (int i = 0; i < combinacion.size(); i++) {
                row[i + 1] = combinacion.get(i);
            }
            tableModel.addRow(row);
        }

        // Agregar la tabla a un JScrollPane y colocarla en el centro
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Crear el panel sur con FlowLayout para los botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnFiltro = new JButton("Salir");
        

        // Agregar botones al panel
        buttonPanel.add(btnFiltro);
        
        btnFiltro.addActionListener(e -> this.dispose());
        
        

        // Agregar el panel sur al marco
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static List<List<String>> combinacionesSinRepeticion(List<String> al, String filtro) {
        List<List<String>> resultado = new ArrayList<>();
        List<String> aux = new ArrayList<>();
        aux.add(filtro); // Iniciar con el entrenamiento específico
        combinacionesSinRepeticionR(al, aux, resultado);
        return resultado;
    }

    public static void combinacionesSinRepeticionR(List<String> al, List<String> aux, List<List<String>> resultado) {
        if (aux.size() == 5) {
            List<String> copia = new ArrayList<>(aux);
            Comparator<String> c = (o1, o2) -> o1.compareToIgnoreCase(o2); // Utilizando Java Funcional
            copia.sort(c);
            if (!resultado.contains(copia)) {
                resultado.add(new ArrayList<>(aux));
            }
        } else {
            for (int i = 0; i < al.size(); i++) {
                if (!aux.contains(al.get(i))) {
                    aux.add(al.get(i));
                    combinacionesSinRepeticionR(al, aux, resultado);
                    aux.remove(aux.size() - 1);
                }
            }
        }
    }
}
