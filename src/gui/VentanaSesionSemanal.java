package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class VentanaSesionSemanal extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaSesionSemanal() {
		
			setTitle("Progreso de Clientes");
	        setBounds(400, 300, 300, 120);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	        List<String> lista = generarSesionAleatoria();
	        
	       
	    }
	 
    public List<String> generarSesionSemanal(List<List<String>> entrenamientos) {
        return generarSesionRecursiva(entrenamientos, entrenamientos.size() - 1, new ArrayList<>());
    }

    private List<String> generarSesionRecursiva(List<List<String>> entrenamientos, int diasRestantes, List<String> sesionActual) {
        // Caso base: si no quedan días por asignar
        if (diasRestantes == 0) {
            return sesionActual;
        }

        // Elegir un entrenamiento al azar para el día actual
        List<String> diaActualEntrenamientos = entrenamientos.get(entrenamientos.size() - diasRestantes);
        String entrenamientoAleatorio = diaActualEntrenamientos.get(new Random().nextInt(diaActualEntrenamientos.size()));

        // Agregar el entrenamiento al día actual
        sesionActual.add(entrenamientoAleatorio);

        // Llamada recursiva para los días restantes
        return generarSesionRecursiva(entrenamientos, diasRestantes - 1, sesionActual);
    }

    
    public List<List<String>> generarEntrenamientos(){
    	// Lista de entrenamientos por día
        List<List<String>> entrenamientosSemanales = new ArrayList<>();
        entrenamientosSemanales.add(List.of("Yoga", "Pilates", "Spinning", "Body Pump", "HIIT"));
        entrenamientosSemanales.add(List.of("Cardio", "Yoga", "TRX", "Zumba", "Boxeo"));
        entrenamientosSemanales.add(List.of("Crossfit", "Body Pump", "Cardio", "Crossfit", "Stretching"));
        entrenamientosSemanales.add(List.of("Zumba", "HIIT", "Power Yoga", "Body Pump", "Spinning"));
        entrenamientosSemanales.add(List.of("Spinning", "Crossfit", "TRX", "HIIT", "Body Pump"));
        entrenamientosSemanales.add(List.of("Boxeo", "Zumba", "Stretching", "Pilates", "Cardio"));
        
        return entrenamientosSemanales;
    }
    
    public List<String> generarSesionAleatoria(){
    	 // Generar una sesión semanal aleatoria
    	List<List<String>> entrenamientosSemanales = generarEntrenamientos();
        List<String> sesionSemanal = generarSesionSemanal(entrenamientosSemanales);
        System.out.println("Sesión semanal generada: " + sesionSemanal);
        return sesionSemanal;
    }
   
   
}
