package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                // Confirmación o lógica adicional antes de cerrar si lo deseas
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
        JButton btnAsistencia = crearBoton("Asistencia");
        JButton btnProgresoClientes = crearBoton("Progreso de Clientes");
        JButton btnRutinas = crearBoton("Rutinas");
        JButton btnHorarios = crearBoton("Horarios");
        JButton btnNotificaciones = crearBoton("Notificaciones");
        JButton btnGestionMonitores = crearBoton("Gestión de Monitores");

        // Añadir botones al panel
        panelBotones.add(btnAsistencia);
        panelBotones.add(btnProgresoClientes);
        panelBotones.add(btnRutinas);
        panelBotones.add(btnHorarios);
        panelBotones.add(btnNotificaciones);
        panelBotones.add(btnGestionMonitores);

        // Acción para el botón "Asistencia"
        btnAsistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana o funcionalidad de Asistencia
                JOptionPane.showMessageDialog(null, "Abrir Asistencia");
            }
        });

        // Acción para el botón "Progreso de Clientes"
        btnProgresoClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana o funcionalidad de Progreso de Clientes
                JOptionPane.showMessageDialog(null, "Abrir Progreso de Clientes");
            }
        });

        // Acción para el botón "Rutinas"
        btnRutinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana o funcionalidad de Rutinas
                JOptionPane.showMessageDialog(null, "Abrir Rutinas");
            }
        });

        // Acción para el botón "Horarios"
        btnHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abrir la ventana de Horarios al hacer clic
                HorariosVentana ventanaHorarios = new HorariosVentana();
                ventanaHorarios.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra la ventana de horarios
            }
        });

        // Acción para el botón "Notificaciones"
        btnNotificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana o funcionalidad de Notificaciones
                JOptionPane.showMessageDialog(null, "Abrir Notificaciones");
            }
        });

        // Acción para el botón "Gestión de Monitores"
        btnGestionMonitores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de gestión de monitores
                new MonitorBd();  // Llamada a la ventana de gestión de monitores
            }
        });

        // Añadir el panel de título y el panel de botones a la ventana principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Añadir el panel principal al contenido de la ventana
        add(panelPrincipal);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para crear un botón con diseño uniforme
    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 18));
        boton.setBackground(new Color(85, 107, 47));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBorderPainted(false);
        boton.setOpaque(true);
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