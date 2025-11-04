package co.edu.uniquindio.universidad.model;

/**
 * Clase que representa a un estudiante en la universidad.
 * Hereda de Persona e incluye información académica específica.
 */
public class Estudiante extends Persona {

    private String carrera;
    private int semestre;
    private double promedio;
    private String codigo;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String identificacion, int edad, String telefono,
                      String carrera, int semestre, double promedio, String codigo) {
        super(nombre, apellido, identificacion, edad, telefono);
        this.carrera = carrera;
        this.semestre = semestre;
        this.promedio = promedio;
        this.codigo = codigo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return super.toString() + ", Estudiante{" +
                "carrera='" + carrera + '\'' +
                ", semestre=" + semestre +
                ", promedio=" + promedio +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}

