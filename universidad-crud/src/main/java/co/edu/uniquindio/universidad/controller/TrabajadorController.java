package co.edu.uniquindio.universidad.controller;

import co.edu.uniquindio.universidad.factory.ModelFactory;
import co.edu.uniquindio.universidad.model.Trabajador;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador de negocio para la gestión de trabajadores.
 * Actúa como intermediario entre la capa de vista y el ModelFactory.
 */
public class TrabajadorController {

    private ModelFactory modelFactory;

    public TrabajadorController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Trabajador> obtenerTrabajadores() {
        return modelFactory.obtenerTrabajadores();
    }

    public Trabajador crearTrabajador(String nombre, String apellido, String identificacion, String edad,
                                       String telefono, String departamento, String cargo, String salario,
                                       LocalDate fechaContratacion) {
        return modelFactory.crearTrabajador(nombre, apellido, identificacion, edad, telefono,
                departamento, cargo, salario, fechaContratacion);
    }

    public boolean actualizarTrabajador(String identificacion, String nombre, String apellido, String edad,
                                         String telefono, String departamento, String cargo, String salario,
                                         LocalDate fechaContratacion) {
        return modelFactory.actualizarTrabajador(identificacion, nombre, apellido, edad, telefono,
                departamento, cargo, salario, fechaContratacion);
    }

    public boolean eliminarTrabajador(String identificacion) {
        return modelFactory.eliminarTrabajador(identificacion);
    }

    public Trabajador buscarTrabajador(String identificacion) {
        return modelFactory.obtenerTrabajador(identificacion);
    }
}

