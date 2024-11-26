package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class GestionPagos extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMonto;
    private JComboBox<String> cmbMetodoPago;
    private JTextArea areaHistorialPagos;
    private ArrayList<String> historialPagos;

    public GestionPagos() {
    	super("Gestión de Pagos");
    	
    	// Inicializar la lista de historial de pagos
    	historialPagos = new ArrayList<>();

    	// Configuración básica de la ventana
    	setSize(500, 450);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    	// Panel principal con margen interno
    	JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
    	panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));

    	// Panel de ingreso de pago con GridBagLayout
    	JPanel panelIngresoPago = new JPanel(new GridBagLayout());
    	panelIngresoPago.setBackground(new Color(240, 248, 255));  // Color suave de fondo
    	panelIngresoPago.setBorder(new LineBorder(new Color(180, 180, 180), 1, true)); // Bordes redondeados
    	        
    	GridBagConstraints gbc = new GridBagConstraints();
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	gbc.insets = new Insets(5, 5, 5, 5);

    	// Cantidad
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	panelIngresoPago.add(new JLabel("Cantidad:"), gbc);

    	txtMonto = new JTextField();
    	gbc.gridx = 1;
    	gbc.gridy = 0;
    	panelIngresoPago.add(txtMonto, gbc);

    	// Método de pago con etiqueta
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	panelIngresoPago.add(new JLabel("Método de Pago:"), gbc);

    	// Crear y personalizar el JComboBox
        cmbMetodoPago = new JComboBox<>(new String[] {"Tarjeta", "Efectivo", "Transferencia"});
        cmbMetodoPago.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cmbMetodoPago.setBackground(new Color(245, 245, 245));
        cmbMetodoPago.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelIngresoPago.add(cmbMetodoPago, gbc);

    	// Botón para añadir pago
    	JButton btnAgregarPago = new JButton("Agregar Pago");
    	btnAgregarPago.setFocusPainted(false);
    	btnAgregarPago.setBackground(new Color(60, 179, 113));
    	btnAgregarPago.setForeground(Color.WHITE);
    	btnAgregarPago.setFont(new Font("SansSerif", Font.BOLD, 14));
    	gbc.gridx = 1;
    	gbc.gridy = 2;
    	gbc.anchor = GridBagConstraints.CENTER;
    	panelIngresoPago.add(btnAgregarPago, gbc);

    	// Historial de pagos con estilo de borde y fondo
    	areaHistorialPagos = new JTextArea(10, 30);
    	areaHistorialPagos.setEditable(false);
    	areaHistorialPagos.setFont(new Font("Monospaced", Font.PLAIN, 12));
    	areaHistorialPagos.setBackground(new Color(245, 245, 245));
    	//areaHistorialPagos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    	JScrollPane scrollHistorial = new JScrollPane(areaHistorialPagos);
        scrollHistorial.setBorder(BorderFactory.createTitledBorder("Historial de Pagos"));
    	
    	// Añadir paneles al panel principal
    	panelPrincipal.add(panelIngresoPago, BorderLayout.NORTH);
    	panelPrincipal.add(scrollHistorial, BorderLayout.CENTER);

    	// Añadir el panel principal a la ventana
    	add(panelPrincipal);

    	// Acción del botón "Agregar Pago"
    	btnAgregarPago.addActionListener(new ActionListener() {
    		@Override
    	    public void actionPerformed(ActionEvent e) {
    			agregarPago();
    	    }
    	});

    	setVisible(true);
    }
    
    private void agregarPago() {
        String monto = txtMonto.getText();
        String metodo = (String) cmbMetodoPago.getSelectedItem();
        
        if (monto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese la cantidad que desee.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Agregar el pago al historial
        String detallePago = "Cantidad: " + monto + ", Método: " + metodo + ", Fecha: " + new Date();
        historialPagos.add(detallePago);
        
        // Actualizar el historial de pagos
        actualizarHistorialPagos();
        
        // Limpiar campos
        txtMonto.setText("");
        cmbMetodoPago.setSelectedIndex(0);
    }
    
    private void actualizarHistorialPagos() {
        areaHistorialPagos.setText("");
        for (String pago : historialPagos) {
            areaHistorialPagos.append(pago + "\n");
        }
    }
}
