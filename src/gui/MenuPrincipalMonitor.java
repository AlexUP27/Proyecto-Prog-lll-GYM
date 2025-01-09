package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;

import javax.swing.table.DefaultTableModel;


import DB.MonitorBd;

import java.awt.*;

public class MenuPrincipalMonitor extends JFrame {
    private static final long serialVersionUID = 1L;

    public MenuPrincipalMonitor() {
        // Configuración de la ventana principal
        setTitle("Menú Principal Monitor");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana, no la aplicación
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose(); // Cierra solo la ventana de horarios
            }
        });

        // Panel principal con un diseño de BorderLayout
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));

        // Panel de título con un fondo agradable
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(34, 45, 50));
        JLabel lblTitulo = new JLabel("Menú Principal", JLabel.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);

        // Panel de botones con un GridLayout ordenado
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1, 15, 15));  // 6 filas, 1 columna, 15px de espacio entre botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Botones del menú con un diseño moderno
        JButton btnAsistencia = crearBoton("Asistencia", e -> new Asistencia());
        JButton btnProgresoClientes = crearBoton("Progreso de Clientes", e -> JOptionPane.showMessageDialog(null, "Abrir Progreso de Clientes"));
        JButton btnRutinas = crearBoton("Rutinas", e -> new VentanaRutinas());
        JButton btnHorarios = crearBoton("Horarios", e -> {
            new VentanaSemana(); // Pasar el modelo al constructor
        });
        JButton btnNotificaciones = crearBoton("Notificaciones", e -> JOptionPane.showMessageDialog(null, "Abrir Notificaciones"));
        JButton btnGestionMonitores = crearBoton("Gestión de Monitores", e -> new MonitorBd());  // Llamada a la ventana de gestión de monitores

        // Añadir botones al panel
        panelBotones.add(btnAsistencia);
        panelBotones.add(btnProgresoClientes);
        panelBotones.add(btnRutinas);
        panelBotones.add(btnHorarios);
        panelBotones.add(btnNotificaciones);
        panelBotones.add(btnGestionMonitores);

        // Añadir el panel de título y el panel de botones a la ventana principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Añadir el panel principal al contenido de la ventana
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para crear un botón con diseño uniforme y acción
    private JButton crearBoton(String texto, java.awt.event.ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 18));
        boton.setBackground(new Color(85, 107, 47));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.addActionListener(accion); // Asignar la acción usando lambda

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(107, 142, 35)); // Cambiar color al pasar el mouse
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(85, 107, 47)); // Restablecer color
            }
        });
        return boton;
    }
}
