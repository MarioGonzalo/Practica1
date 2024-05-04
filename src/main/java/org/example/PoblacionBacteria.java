package org.example;

import java.util.Date;

public class PoblacionBacteria {
    String nombre;
    Experimento exp;
    Date fechaInicio;
    Date fechaFin;
    int numInicialBacteria;
    int numActualBacteria;
    int temperatura;
    String luminosidad;

    public PoblacionBacteria(String nombre, Experimento exp, Date fechaInicio, Date fechaFin, int numInicialBacteria, int numActualBacteria, int temperatura, String luminosidad) {
        this.nombre = nombre;
        this.exp = exp;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.numInicialBacteria = numInicialBacteria;
        this.numActualBacteria = numActualBacteria;
        this.temperatura = temperatura;
        this.luminosidad = luminosidad;
    }

    int obtenerCantidadComidaDia(int nd) {
        return exp.getCantidadComidaDia(nd);
    }

    void alimentar(int dia){
        numActualBacteria = numInicialBacteria * (1+obtenerCantidadComidaDia(dia));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Experimento getExp() {
        return exp;
    }

    public void setExp(Experimento exp) {
        this.exp = exp;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumInicialBacteria() {
        return numInicialBacteria;
    }

    public void setNumInicialBacteria(int numInicialBacteria) {
        this.numInicialBacteria = numInicialBacteria;
    }

    public int getNumActualBacteria() {
        return numActualBacteria;
    }

    public void setNumActualBacteria(int numActualBacteria) {
        this.numActualBacteria = numActualBacteria;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getLuminosidad() {
        return luminosidad;
    }

    public void setTipoLuminosidad(String luminosidad) {
        this.luminosidad = luminosidad;
    }


}
