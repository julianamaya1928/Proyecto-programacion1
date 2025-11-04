package co.edu.uniquindio.universidad.controller;

import co.edu.uniquindio.universidad.factory.ModelFactory;
import co.edu.uniquindio.universidad.model.Externo;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador de negocio para la gestión de externos.
 * Actúa como intermediario entre la capa de vista y el ModelFactory.
 */
public class ExternoController {

    private ModelFactory modelFactory;

    public ExternoController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Externo> obtenerExternos() {
        return modelFactory.obtenerExternos();
    }

    public Externo crearExterno(String nombre, String apellido, String identificacion, String edad,
                                 String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        return modelFactory.crearExterno(nombre, apellido, identificacion, edad, telefono,
                empresa, proyectoAsignado, fechaIngreso);
    }

    public boolean actualizarExterno(String identificacion, String nombre, String apellido, String edad,
                                      String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        return modelFactory.actualizarExterno(identificacion, nombre, apellido, edad, telefono,
                empresa, proyectoAsignado, fechaIngreso);
    }

    public boolean eliminarExterno(String identificacion) {
        return modelFactory.eliminarExterno(identificacion);
    }

    public Externo buscarExterno(String identificacion) {
        return modelFactory.obtenerExterno(identificacion);
    }
}

