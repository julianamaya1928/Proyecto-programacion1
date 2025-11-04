package co.edu.uniquindio.universidad.model;

import java.time.LocalDate;

/**
 * Clase que representa a un trabajador externo asociado a la universidad.
 * Hereda de Persona e incluye información de la empresa y proyecto asignado.
 */
public class Externo extends Persona {

    private String empresa;
    private String proyectoAsignado;
    private LocalDate fechaIngreso;

    public Externo() {
    }

    public Externo(String nombre, String apellido, String identificacion, int edad, String telefono,
                   String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        super(nombre, apellido, identificacion, edad, telefono);
        this.empresa = empresa;
        this.proyectoAsignado = proyectoAsignado;
        this.fechaIngreso = fechaIngreso;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getProyectoAsignado() {
        return proyectoAsignado;
    }

    public void setProyectoAsignado(String proyectoAsignado) {
        this.proyectoAsignado = proyectoAsignado;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return super.toString() + ", Externo{" +
                "empresa='" + empresa + '\'' +
                ", proyectoAsignado='" + proyectoAsignado + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}

