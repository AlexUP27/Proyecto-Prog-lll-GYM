package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Monitor;

public class InformacionMonitores extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InformacionMonitores() {
		setTitle("Informacion Monitor");
		setSize(400,300);
		
		List<Monitor> monitores = List.of(
				new Monitor(111, "Jorge", "Garcia", "Jorge.Garcia@gmail.com", 28, "Yoga", 673984609),
				new Monitor(112, "Irene", "Sanchez", "Irene.Sanchez@gmail.com", 35, "Pilates", 639401843),
				new Monitor(113, "David", "Alvarez", "David.Alvarez@gmail.com", 24, "Spinning", 673928356),
				new Monitor(114, "Daniel", "Hernandez", "Daniel.Hernandez@gmail.com", 42, "Boxeo", 678549807),
				new Monitor(115, "Raul", "Martinez", "Raul.Martinez@gmail.com", 31, "Zumba", 678643789),
				new Monitor(116, "Laura", "Ruiz", "Laura.Ruiz@gmail.com", 26, "HIIT", 654646746),
				new Monitor(116, "Iker", "Ruiz", "Iker.Ruiz@gmail.com", 27, "Crossfit", 694846726)
				);
		
		setTitle("Informacion de monitores");
		setSize(600, 400);
		
		MonitorTabla modelo = new MonitorTabla(monitores, null);
		JTable Jtable = new JTable(modelo);
		
		JScrollPane scrollPane = new JScrollPane(Jtable);
		add(scrollPane, BorderLayout.CENTER);
		
		JButton botonCerrar = new JButton("Cerrar");
		add(botonCerrar, BorderLayout.SOUTH);
		
		botonCerrar.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
            	dispose();
        	}
    	});
		
		setVisible(true);
	}
}
