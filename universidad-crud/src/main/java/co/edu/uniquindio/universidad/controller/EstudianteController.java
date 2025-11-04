package co.edu.uniquindio.universidad.controller;

import co.edu.uniquindio.universidad.factory.ModelFactory;
import co.edu.uniquindio.universidad.model.Estudiante;

import java.util.List;

/**
 * Controlador de negocio para la gestión de estudiantes.
 * Actúa como intermediario entre la capa de vista y el ModelFactory.
 */
public class EstudianteController {

    private ModelFactory modelFactory;

    public EstudianteController() {
        modelFactory = ModelFactory.getInstancia();
    }

    public List<Estudiante> obtenerEstudiantes() {
        return modelFactory.obtenerEstudiantes();
    }

    public Estudiante crearEstudiante(String nombre, String apellido, String identificacion, String edad,
                                       String telefono, String carrera, String semestre, String promedio, String codigo) {
        return modelFactory.crearEstudiante(nombre, apellido, identificacion, edad, telefono,
                carrera, semestre, promedio, codigo);
    }

    public boolean actualizarEstudiante(String identificacion, String nombre, String apellido, String edad,
                                         String telefono, String carrera, String semestre, String promedio, String codigo) {
        return modelFactory.actualizarEstudiante(identificacion, nombre, apellido, edad, telefono,
                carrera, semestre, promedio, codigo);
    }

    public boolean eliminarEstudiante(String identificacion) {
        return modelFactory.eliminarEstudiante(identificacion);
    }

    public Estudiante buscarEstudiante(String identificacion) {
        return modelFactory.obtenerEstudiante(identificacion);
    }
}

