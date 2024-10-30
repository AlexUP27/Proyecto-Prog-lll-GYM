package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bin.Cliente;
import bin.Cliente.Genre;

public class InformacionClientes extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InformacionClientes() {
		List<Cliente> clientes = List.of(
	    		new Cliente("Carlos", "Rodriguez", 677283945, "Carlos.Rodriguez@gmail.com","GRAN VIA", 22,Genre.MALE),
	    		new Cliente("Hugo", "García", 677283945, "Hugo.Garcia@gmail.com","CALLE LEONARDO PRIETO", 32,Genre.MALE),
	    		new Cliente("Lucía", "García", 677283945, "Lucia.Garcia@gmail.com","CALLE MONTALBAN", 18,Genre.FEMALE),
	    		new Cliente("Sofia", "González", 677283945, "Sofia.González@gmail.com","PASEO FERNAN NUÑEZ", 20,Genre.FEMALE),
	    		new Cliente("Ana", "Fernández", 677283945, "Ana.Fernández@gmail.com","GRAN VIA", 29,Genre.FEMALE),
	    		new Cliente("Martín", "López", 677283945, "Martín.López@gmail.com","CALLE HEXAGONOS", 16,Genre.MALE),
	    		new Cliente("Marta", "Martínez", 677283945, "Marta.Martínez@gmail.com","PASEO FERNAN NUÑEZ", 19,Genre.FEMALE),
	    		new Cliente("Lucas", "Rodriguez", 677283945, "Lucas.Rodriguez@gmail.com","CALLE DOCTOR TOLOSA LATOUR", 19,Genre.MALE),
	    		new Cliente("Monica", "Sánchez", 677283945, "Monica.Sánchez@gmail.com","PARQUE CASA DE CAMPO ", 26,Genre.FEMALE),
	    		new Cliente("Mateo", "Martínez", 677283945, "Mateo.Martínez@gmail.com","GRAN VIA", 50,Genre.MALE),
	    		new Cliente("Claudia", "Pérez", 677283945, "Claudia.Pérez@gmail.com","CALLE MONTALBAN", 34,Genre.FEMALE),
	    		new Cliente("Carla", "Diaz", 677283945, "Carla.Diaz@gmail.com","PARQUE CASA DE CAMPO ", 20,Genre.FEMALE),
	    		new Cliente("Leo", "Gomez", 677283945, "Leo.Gomez@gmail.com","PASEO FERNAN NUÑEZ", 42,Genre.MALE),
	    		new Cliente("Daniel", "Sánchez", 677283945, "Daniel.Sánchez@gmail.com","CAMINO SALMEDINA", 58,Genre.MALE),
	    		new Cliente("Maria", "Rodriguez", 677283945, "Maria.Rodriguez@gmail.com","PASEO FERNAN NUÑEZ", 17,Genre.FEMALE)
	    );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Informacion de clientes");
		setSize(700, 500);
		
		ClienteTabla modelo = new ClienteTabla(clientes, null);
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
