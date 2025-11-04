package co.edu.uniquindio.universidad.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase principal que representa la universidad y gestiona las listas de
 * estudiantes, externos y trabajadores.
 */
public class Universidad {

    private String nombre;
    private ArrayList<Estudiante> listaEstudiantes;
    private ArrayList<Externo> listaExternos;
    private ArrayList<Trabajador> listaTrabajadores;

    public Universidad() {
        this.listaEstudiantes = new ArrayList<>();
        this.listaExternos = new ArrayList<>();
        this.listaTrabajadores = new ArrayList<>();
    }

    public Universidad(String nombre) {
        this();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public ArrayList<Externo> getListaExternos() {
        return listaExternos;
    }

    public ArrayList<Trabajador> getListaTrabajadores() {
        return listaTrabajadores;
    }

    // ========== CRUD ESTUDIANTES ==========

    public Estudiante crearEstudiante(String nombre, String apellido, String identificacion, int edad,
                                       String telefono, String carrera, int semestre, double promedio, String codigo) {
        Estudiante estudianteEncontrado = obtenerEstudiante(identificacion);
        if (estudianteEncontrado == null) {
            Estudiante estudiante = new Estudiante(nombre, apellido, identificacion, edad, telefono,
                    carrera, semestre, promedio, codigo);
            listaEstudiantes.add(estudiante);
            return estudiante;
        }
        return null;
    }

    public boolean actualizarEstudiante(String identificacion, String nombre, String apellido, int edad,
                                         String telefono, String carrera, int semestre, double promedio, String codigo) {
        Estudiante estudiante = obtenerEstudiante(identificacion);
        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setEdad(edad);
            estudiante.setTelefono(telefono);
            estudiante.setCarrera(carrera);
            estudiante.setSemestre(semestre);
            estudiante.setPromedio(promedio);
            estudiante.setCodigo(codigo);
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(String identificacion) {
        Estudiante estudiante = obtenerEstudiante(identificacion);
        if (estudiante != null) {
            listaEstudiantes.remove(estudiante);
            return true;
        }
        return false;
    }

    public Estudiante obtenerEstudiante(String identificacion) {
        for (Estudiante estudiante : listaEstudiantes) {
            if (estudiante.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return estudiante;
            }
        }
        return null;
    }

    // ========== CRUD EXTERNOS ==========

    public Externo crearExterno(String nombre, String apellido, String identificacion, int edad,
                                 String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        Externo externoEncontrado = obtenerExterno(identificacion);
        if (externoEncontrado == null) {
            Externo externo = new Externo(nombre, apellido, identificacion, edad, telefono,
                    empresa, proyectoAsignado, fechaIngreso);
            listaExternos.add(externo);
            return externo;
        }
        return null;
    }

    public boolean actualizarExterno(String identificacion, String nombre, String apellido, int edad,
                                      String telefono, String empresa, String proyectoAsignado, LocalDate fechaIngreso) {
        Externo externo = obtenerExterno(identificacion);
        if (externo != null) {
            externo.setNombre(nombre);
            externo.setApellido(apellido);
            externo.setEdad(edad);
            externo.setTelefono(telefono);
            externo.setEmpresa(empresa);
            externo.setProyectoAsignado(proyectoAsignado);
            externo.setFechaIngreso(fechaIngreso);
            return true;
        }
        return false;
    }

    public boolean eliminarExterno(String identificacion) {
        Externo externo = obtenerExterno(identificacion);
        if (externo != null) {
            listaExternos.remove(externo);
            return true;
        }
        return false;
    }

    public Externo obtenerExterno(String identificacion) {
        for (Externo externo : listaExternos) {
            if (externo.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return externo;
            }
        }
        return null;
    }

    // ========== CRUD TRABAJADORES ==========

    public Trabajador crearTrabajador(String nombre, String apellido, String identificacion, int edad,
                                       String telefono, String departamento, String cargo, double salario,
                                       LocalDate fechaContratacion) {
        Trabajador trabajadorEncontrado = obtenerTrabajador(identificacion);
        if (trabajadorEncontrado == null) {
            Trabajador trabajador = new Trabajador(nombre, apellido, identificacion, edad, telefono,
                    departamento, cargo, salario, fechaContratacion);
            listaTrabajadores.add(trabajador);
            return trabajador;
        }
        return null;
    }

    public boolean actualizarTrabajador(String identificacion, String nombre, String apellido, int edad,
                                         String telefono, String departamento, String cargo, double salario,
                                         LocalDate fechaContratacion) {
        Trabajador trabajador = obtenerTrabajador(identificacion);
        if (trabajador != null) {
            trabajador.setNombre(nombre);
            trabajador.setApellido(apellido);
            trabajador.setEdad(edad);
            trabajador.setTelefono(telefono);
            trabajador.setDepartamento(departamento);
            trabajador.setCargo(cargo);
            trabajador.setSalario(salario);
            trabajador.setFechaContratacion(fechaContratacion);
            return true;
        }
        return false;
    }

    public boolean eliminarTrabajador(String identificacion) {
        Trabajador trabajador = obtenerTrabajador(identificacion);
        if (trabajador != null) {
            listaTrabajadores.remove(trabajador);
            return true;
        }
        return false;
    }

    public Trabajador obtenerTrabajador(String identificacion) {
        for (Trabajador trabajador : listaTrabajadores) {
            if (trabajador.getIdentificacion().equalsIgnoreCase(identificacion)) {
                return trabajador;
            }
        }
        return null;
    }
}

