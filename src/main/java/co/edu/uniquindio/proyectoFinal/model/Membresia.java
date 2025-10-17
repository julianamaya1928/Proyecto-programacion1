package co.edu.uniquindio.proyectoFinal.model;

import java.time.LocalDate;

public abstract class Membresia {
    protected String tipo;
    protected double costo;
    protected LocalDate fechaInicio;
    protected LocalDate fechaVencimiento;
    protected boolean activa;

    public Membresia(String tipo, double costo, LocalDate fechaInicio, LocalDate fechaVencimiento, boolean activa) {
        this.tipo = tipo;
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.activa = activa;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean estaVigente() {
        return activa && LocalDate.now().isBefore(fechaVencimiento);
    }

    public abstract double calcularCosto();

    @Override
    public String toString() {
        return tipo + " - $" + costo;
    }
}

