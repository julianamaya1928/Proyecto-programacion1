package co.edu.uniquindio.proyectoFinal.model;

public class Persona {
    protected String nombre;
    protected String identificacion;
    protected int edad;
    protected String telefono;
    protected Membresia membresia;

    public Persona(String nombre, String identificacion, int edad, String telefono, Membresia membresia) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.telefono = telefono;
        this.membresia = membresia;
    }

    public Persona() {
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void asignarMembresia(Membresia m) {
        this.membresia = m;
    }

    @Override
    public String toString() {
        return nombre + " (" + identificacion + ") - " + membresia.getTipo();
    }
}


