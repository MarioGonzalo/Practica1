package org.example;

public class Main {
    public static void main(String[] args) {
        Experimento exp = new Experimento(1, "medio1");
        PoblacionBacteria poblacion = new PoblacionBacteria("bacteria1", exp, null, null, 100, 100, 37, null);
        exp.addPoblacBacteria(poblacion);
        System.out.println(poblacion.obtenerCantidadComidaDia(5));
        poblacion.alimentar(5);
        System.out.println(poblacion.numActualBacteria);
        System.out.println(poblacion.getFechaFin());
    }
}