package main;

import gui.BDInicioSesion;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		VentanaInicial v = new VentanaInicial();
		
		 // Inicializar la base de datos
        BDInicioSesion.initBD();
        
        // Crear las tablas necesarias (solo una vez)
        BDInicioSesion.crearTabla();

        System.out.println("Base de datos inicializada correctamente.");
		
	}

}
