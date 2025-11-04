package co.edu.uniquindio.universidad.factory;

import co.edu.uniquindio.universidad.model.*;
import co.edu.uniquindio.universidad.utils.DataUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Clase Factory que implementa el patrón Singleton.
 * Centraliza el acceso al modelo de datos de la universidad.
 */
public class ModelFactory {

    private static ModelFactory modelFactory;
    private Universidad universidad;

    public static ModelFactory getInstancia() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    private ModelFactory() {
        universidad = DataUtil.inicializarDatos();
    }

    public Universidad getUniversidad() {
        return universidad;
    }

    // ========== DELEGADOS PARA ESTUDIANTES ==========

    public List<Estudiante> obtenerEstudiantes() {
        return universidad.getListaEstudiantes();
    }

    public Estudiante crearEstudiante(String nombre, String apellido, String identificacion, String edad,
                                       String telefono, String carrera, String semestre, String promedio, String codigo) {
        try {
            return universidad.crearEstudiante(nombre, apellido, identificacion, Integer.parseInt(edad),
                    telefono, carrera, Integer.parseInt(semestre), Double.parseDouble(promedio), codigo);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean actualizarEstudiante(String identificacion, String nombre, String apellido, String edad,
                                         String telefono, String carrera, String semestre, String promedio, String codigo) {
        try {
            return universidad.actualizarEstudiante(identificacion, nombre, apellido, Integer.parseInt(edad),
                    telefono, carrera, Integer.parseInt(semestre), Double.parseDouble(promedio), codigo);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean eliminarEstudiante(String identificacion) {
        return universidad.eliminarEstudiante(identificacion);
    }

    public Estudiante obtenerEstudiante(String identificacion) {
        return universidad.obtenerEstudiante(identificacion);
    }

    // ========== DELEGADOS PARA EXTERNOS ==========

    public List<Externo> obtenerExternos() {
        return universidad.getListaExternos();
    }

    public Externo crearExterno(String nombre, String apellido, String identificacion, String edad,
                                 String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        try {
            return universidad.crearExterno(nombre, apellido, identificacion, Integer.parseInt(edad),
                    telefono, empresa, proyectoAsignado, fechaIngreso);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean actualizarExterno(String identificacion, String nombre, String apellido, String edad,
                                      String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        try {
            return universidad.actualizarExterno(identificacion, nombre, apellido, Integer.parseInt(edad),
                    telefono, empresa, proyectoAsignado, fechaIngreso);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean eliminarExterno(String identificacion) {
        return universidad.eliminarExterno(identificacion);
    }

    public Externo obtenerExterno(String identificacion) {
        return universidad.obtenerExterno(identificacion);
    }

    // ========== DELEGADOS PARA TRABAJADORES ==========

    public List<Trabajador> obtenerTrabajadores() {
        return universidad.getListaTrabajadores();
    }

    public Trabajador crearTrabajador(String nombre, String apellido, String identificacion, String edad,
                                       String telefono, String departamento, String cargo, String salario,
                                       LocalDate fechaContratacion) {
        try {
            return universidad.crearTrabajador(nombre, apellido, identificacion, Integer.parseInt(edad),
                    telefono, departamento, cargo, Double.parseDouble(salario), fechaContratacion);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public boolean actualizarTrabajador(String identificacion, String nombre, String apellido, String edad,
                                         String telefono, String departamento, String cargo, String salario,
                                         LocalDate fechaContratacion) {
        try {
            return universidad.actualizarTrabajador(identificacion, nombre, apellido, Integer.parseInt(edad),
                    telefono, departamento, cargo, Double.parseDouble(salario), fechaContratacion);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean eliminarTrabajador(String identificacion) {
        return universidad.eliminarTrabajador(identificacion);
    }

    public Trabajador obtenerTrabajador(String identificacion) {
        return universidad.obtenerTrabajador(identificacion);
    }
}

