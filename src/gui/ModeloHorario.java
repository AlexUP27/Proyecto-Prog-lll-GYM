package gui;

import java.util.ArrayList;
import java.util.List;

public class ModeloHorario {
    // Lista de horarios representados por clases (un String[][] de datos)
    private String[][] horarios;

    // Constructor para inicializar el horario con datos predeterminados
    public ModeloHorario() {
        this.horarios = new String[][]{
            {"08:00 - 09:00", "Yoga", "Pilates", "Spinning", "Body Pump", "HIIT"},
            {"09:00 - 10:00", "Cardio", "Yoga", "TRX", "Zumba", "Boxeo"},
            {"10:00 - 11:00", "Crossfit", "Body Pump", "Cardio", "Crossfit", "Stretching"},
            {"11:00 - 12:00", "Zumba", "HIIT", "Power Yoga", "Boxeo", "Spinning"},
            {"12:00 - 13:00", "Spinning", "Crossfit", "TRX", "HIIT", "Body Pump"},
            {"13:00 - 14:00", "Boxeo", "Zumba", "Stretching", "Pilates", "Cardio"},
            {"14:00 - 15:00", "Descanso", "Descanso", "Descanso", "Descanso", "Descanso"},
            {"15:00 - 16:00", "Yoga", "Body Pump", "Spinning", "TRX", "HIIT"},
            {"16:00 - 17:00", "Cardio", "Yoga", "Spinning", "Zumba", "Body Combat"},
            {"17:00 - 18:00", "Crossfit", "TRX", "Cardio", "Crossfit", "Stretching"},
            {"18:00 - 19:00", "Body Combat", "HIIT", "Yoga", "Boxeo", "Spinning"},
            {"19:00 - 20:00", "Power Yoga", "Crossfit", "Pilates", "HIIT", "TRX"},
            {"20:00 - 21:00", "Boxeo", "Zumba", "Crossfit", "Pilates", "Cardio"},
            {"21:00 - 22:00", "HIIT", "Stretching", "Boxeo", "Cardio", "Body Pump"}
        };
    }

    public String[][] getHorarios() {
        return horarios;
    }

    public void setHorarios(String[][] horarios) {
        this.horarios = horarios;
    }

    // Métodos para modificar el horario
    public void modificarClase(int fila, int columna, String nuevoValor) {
        if (fila >= 0 && fila < horarios.length && columna >= 0 && columna < horarios[0].length) {
            horarios[fila][columna] = nuevoValor;
        }
    }

    public void insertarClase(int fila, String[] nuevaFila) {
        // Aquí agregaríamos lógica para insertar una nueva fila, por ejemplo
        List<String[]> listHorarios = new ArrayList<>();
        for (String[] filaHorarios : horarios) {
            listHorarios.add(filaHorarios);
        }
        listHorarios.add(nuevaFila);
        horarios = listHorarios.toArray(new String[0][0]);
    }

    public void eliminarClase(int fila, int columna) {
        if (fila >= 0 && fila < horarios.length) {
            horarios[fila][columna] = ""; // Vaciar la celda
        }
    }
}
