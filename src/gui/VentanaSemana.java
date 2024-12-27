package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DB.DiasSemanaBD;

public class VentanaSemana extends JFrame{

	
	private static final long serialVersionUID = 1L;

	public VentanaSemana(ModeloHorario Horario) {
        
		super("Tipos de Entrenamiento");
        
        // Configuración básica de la ventana
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(5, 1, 10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tipos de entrenamiento
        JButton btnLunes = new JButton("LUNES");
        btnLunes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnLunes.setBackground(Color.BLUE);
        btnLunes.setForeground(Color.WHITE);
        btnLunes.setFocusPainted(false); // Sin borde al seleccionar
        
        JButton btnMartes = new JButton("MARTES");
        btnMartes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnMartes.setBackground(Color.BLUE);
        btnMartes.setForeground(Color.WHITE);
        btnMartes.setFocusPainted(false); // Sin borde al seleccionar
        
        JButton btnMiercoles = new JButton("MIERCOLES");
        btnMiercoles.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnMiercoles.setBackground(Color.BLUE);
        btnMiercoles.setForeground(Color.WHITE);
        btnMiercoles.setFocusPainted(false); // Sin borde al seleccionar

        JButton btnJueves = new JButton("JUEVES");
        btnJueves.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnJueves.setBackground(Color.BLUE);
        btnJueves.setForeground(Color.WHITE);
        btnJueves.setFocusPainted(false); // Sin borde al seleccionar

        JButton btnViernes = new JButton("VIERNES");
        btnViernes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnViernes.setBackground(Color.BLUE);
        btnViernes.setForeground(Color.WHITE);
        btnViernes.setFocusPainted(false); // Sin borde al seleccionar

        // Añadir botones al panel
        panelPrincipal.add(btnLunes);
        panelPrincipal.add(btnMartes);
        panelPrincipal.add(btnMiercoles);
        panelPrincipal.add(btnJueves);
        panelPrincipal.add(btnViernes);
       
        getContentPane().add(panelPrincipal);
    
        setVisible(true);
       
        btnLunes.addActionListener(e -> new DiasSemanaBD("Lunes"));
        btnMartes.addActionListener(e -> new DiasSemanaBD("Martes"));
        btnMiercoles.addActionListener(e -> new DiasSemanaBD("Miercoles"));
        btnJueves.addActionListener(e -> new DiasSemanaBD("Jueves"));
        btnViernes.addActionListener(e -> new DiasSemanaBD("Viernes"));
        
        
        
	}
}
