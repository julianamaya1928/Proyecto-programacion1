package co.edu.uniquindio.universidad.model;

import java.time.LocalDate;

/**
 * Clase que representa a un trabajador de la universidad.
 * Hereda de Persona e incluye información laboral específica.
 */
public class Trabajador extends Persona {

    private String departamento;
    private String cargo;
    private double salario;
    private LocalDate fechaContratacion;

    public Trabajador() {
    }

    public Trabajador(String nombre, String apellido, String identificacion, int edad, String telefono,
                      String departamento, String cargo, double salario, LocalDate fechaContratacion) {
        super(nombre, apellido, identificacion, edad, telefono);
        this.departamento = departamento;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return super.toString() + ", Trabajador{" +
                "departamento='" + departamento + '\'' +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", fechaContratacion=" + fechaContratacion +
                '}';
    }
}

