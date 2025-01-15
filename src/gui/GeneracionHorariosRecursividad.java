package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GeneracionHorariosRecursividad {

    public static List<List<String>> generarCombinaciones(List<String> elementos) {
        List<List<String>> resultado = new ArrayList<>();
        generarCombinacionesR(elementos, new ArrayList<>(), resultado);
        return resultado;
    }

    private static void generarCombinacionesR(List<String> elementos, List<String> actual, List<List<String>> resultado) {
        //Caso base
        if (actual.size() == 5) {
            List<String> copia = new ArrayList<>(actual);
            copia.sort(Comparator.naturalOrder());
            if (!resultado.contains(copia)) {
                resultado.add(copia);
            }
            return;
        }

        //Caso recursivo
        for (String elemento : elementos) {
            if (!actual.contains(elemento)) {
                actual.add(elemento);
                generarCombinacionesR(elementos, actual, resultado);
                actual.remove(actual.size() - 1); // Backtracking
            }
        }
    }

    public static void main(String[] args) {
        // Lista de tipos de entrenamientos
        List<String> entrenamientos = Arrays.asList("Yoga", "Pilates", "Spinning", "Body Pump", "HIIT", "Power Yoga",
                "Cardio", "TRX", "Zumba", "Boxeo", "Crossfit", "Stretching", "Body Combat");

        // Generar combinaciones usando recursividad
        List<List<String>> combinaciones = generarCombinaciones(entrenamientos);

        // Imprimir las combinaciones
        System.out.println("Todas las combinaciones posibles:");
        for (int i = 0; i < combinaciones.size(); i++) {
            System.out.println("Combinación " + (i + 1) + ": " + combinaciones.get(i));
        }
    }
}
