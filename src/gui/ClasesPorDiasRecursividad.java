package gui;

public class ClasesPorDiasRecursividad {

    public static void generarReporte(String[][] horario, int fila, int columna) {
        
    	//Caso base
    	if (fila >= horario.length) {
            return;
        }
    	
    	//Caso base 2
        if (columna >= horario[0].length) {
            System.out.println("--- Fin del Día ---");
            generarReporte(horario, fila + 1, 1); // Pasar al siguiente día
            return;
        }
        
        //Caso recursivo
        if (!horario[fila][columna].equals("Descanso")) {
            System.out.println(horario[fila][0] + ": " + horario[fila][columna]);
        }
        generarReporte(horario, fila, columna + 1);
    }

    public static void main(String[] args) {
        //Ejemplo 1
        String[][] horario1 = {
            {"08:00 - 09:00", "Yoga", "Descanso", "Spinning"},
            {"09:00 - 10:00", "Zumba", "Pilates", "Crossfit"}
        };

        System.out.println("Reporte del horario 1:");
        generarReporte(horario1, 0, 1);

        System.out.println("\n=========================\n");

        //Ejemplo 2
        String[][] horario2 = {
            {"07:00 - 08:00", "Power Yoga", "TRX", "Boxeo"},
            {"08:00 - 09:00", "HIIT", "Descanso", "Body Combat"},
            {"09:00 - 10:00", "Zumba", "Stretching", "Descanso"}
        };

        System.out.println("Reporte del horario 2:");
        generarReporte(horario2, 0, 1);

        System.out.println("\n=========================\n");

        //Ejemplo 3
        String[][] horario3 = {
            {"06:00 - 07:00", "Cardio", "Yoga", "Crossfit"},
            {"07:00 - 08:00", "Spinning", "Pilates", "TRX"},
            {"08:00 - 09:00", "Descanso", "HIIT", "Body Pump"},
            {"09:00 - 10:00", "Zumba", "Boxeo", "Stretching"}
        };
        
        System.out.println("Reporte del horario 3:");
        generarReporte(horario3, 0, 1);
        
        System.out.println("\n=========================\n");
        
        //Ejemplo 4
        String[][] horario4 = {
        	    {"06:00 - 07:00", "Cardio", "Body Pump", "Yoga"},
        	    {"07:00 - 08:00", "Spinning", "Zumba", "Crossfit"},
        	    {"08:00 - 09:00", "HIIT", "Power Yoga", "Boxeo"}
        };

        System.out.println("Reporte del horario 4:");
        generarReporte(horario4, 0, 1);

        System.out.println("\n=========================\n");
        
        //Ejemplo 5
        String[][] horario5 = {
        	    {"07:00 - 08:00", "Descanso", "Yoga", "Descanso"},
        	    {"08:00 - 09:00", "Descanso", "Descanso", "Body Combat"},
        	    {"09:00 - 10:00", "Stretching", "Descanso", "HIIT"}
        };

        System.out.println("Reporte del horario 5:");
        generarReporte(horario5, 0, 1);

        System.out.println("\n=========================\n");
    }
}

