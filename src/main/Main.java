package main;

import DB.BDInicioSesion;
import DB.BDRutinas;
import gui.VentanaInicial;

public class Main {

	public static void main(String[] args) {
		new VentanaInicial();
		
		 // Inicializar la base de datos
        BDInicioSesion.initBD();
        // Crear las tablas necesarias (solo una vez)
        BDInicioSesion.crearTabla();
        
        BDRutinas.initBD();
        BDRutinas.crearTabla();
        

        System.out.println("Base de datos inicializada correctamente.");
		
	}

}
