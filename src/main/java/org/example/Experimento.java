package org.example;

import java.util.ArrayList;
import java.util.List;

public class Experimento {
    private static final int MAX_COMIDA = 300;
    private int[] cantidadesComida;
    private int idExperimento;
    private List<PoblacionBacteria> cultivos;

    public Experimento(int idExperimento) {
        this.idExperimento = idExperimento;
        cantidadesComida = new int[30];
        for (int i = 0; i < cantidadesComida.length; i++) {
            cantidadesComida[i] = 3 * i + 100;
        }
        cultivos = new ArrayList<>();
    }

    public void addPoblacBacteria(PoblacionBacteria poblacion) {
        cultivos.add(poblacion);
    }

    public int getCantidadComidaDia(int nd) {
        // Verificar si nd está dentro del rango válido
        if (nd >= 0 && nd < cantidadesComida.length) {
            return cantidadesComida[nd];
        } else {
            // Enviar algún tipo de indicación de error, como -1
            return -1;
        }
    }
    public int getIdExperimento() {
        return idExperimento;
    }

    public void setIdExperimento(int idExperimento) {
        this.idExperimento = idExperimento;
    }
}
