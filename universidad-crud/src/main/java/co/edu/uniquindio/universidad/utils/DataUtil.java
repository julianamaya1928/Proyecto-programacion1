package co.edu.uniquindio.universidad.utils;

import co.edu.uniquindio.universidad.model.*;

import java.time.LocalDate;

/**
 * Clase utilitaria para inicializar datos de prueba en el sistema.
 */
public class DataUtil {

    /**
     * Inicializa la universidad con datos de prueba para estudiantes, externos y trabajadores.
     *
     * @return Universidad con datos predefinidos
     */
    public static Universidad inicializarDatos() {
        Universidad universidad = new Universidad("Universidad del Quindío");

        // Crear estudiantes de ejemplo
        universidad.crearEstudiante("Juan", "Pérez", "1094123456", 20, "3001234567",
                "Ingeniería de Sistemas", 5, 4.2, "201920001");
        universidad.crearEstudiante("María", "González", "1094567890", 22, "3009876543",
                "Administración de Empresas", 7, 3.8, "201920002");
        universidad.crearEstudiante("Carlos", "Rodríguez", "1094789012", 19, "3005551234",
                "Derecho", 3, 4.5, "201920003");

        // Crear externos de ejemplo
        universidad.crearExterno("Ana", "Martínez", "1080123456", 35, "3001112222",
                "Tech Solutions S.A.", "Implementación de sistema académico",
                LocalDate.of(2024, 1, 15));
        universidad.crearExterno("Luis", "Hernández", "1080456789", 28, "3004445555",
                "Global Consulting", "Auditoría de procesos",
                LocalDate.of(2024, 3, 1));

        // Crear trabajadores de ejemplo
        universidad.crearTrabajador("Pedro", "García", "1080987654", 42, "3007778888",
                "Recursos Humanos", "Director de RRHH", 5000000.0,
                LocalDate.of(2015, 6, 10));
        universidad.crearTrabajador("Laura", "Sánchez", "1080321654", 38, "3009990000",
                "Contabilidad", "Contador Senior", 4500000.0,
                LocalDate.of(2018, 2, 20));

        return universidad;
    }
}

