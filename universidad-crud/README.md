# Sistema de Gestión Universitaria - CRUD

Aplicación de escritorio desarrollada con JavaFX para la gestión de estudiantes, externos y trabajadores de una universidad.

## 📋 Descripción

Este proyecto implementa un sistema completo de CRUD (Create, Read, Update, Delete) para tres entidades principales:

- **Estudiantes**: Gestión de información académica de estudiantes
- **Externos**: Gestión de trabajadores externos y sus proyectos
- **Trabajadores**: Gestión de personal de la universidad

## 🛠️ Tecnologías

- **Java**: 22
- **JavaFX**: 22.0.1
- **Maven**: Build tool
- **Arquitectura**: MVC (Model-View-Controller)

## 📁 Estructura del Proyecto

```
universidad-crud/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── module-info.java
│   │   │   └── co/edu/uniquindio/universidad/
│   │   │       ├── UniversidadApp.java              # Punto de entrada
│   │   │       ├── model/                           # Capa de Modelo
│   │   │       │   ├── Persona.java (abstracta)
│   │   │       │   ├── Estudiante.java
│   │   │       │   ├── Externo.java
│   │   │       │   ├── Trabajador.java
│   │   │       │   └── Universidad.java
│   │   │       ├── controller/                      # Controladores de Negocio
│   │   │       │   ├── EstudianteController.java
│   │   │       │   ├── ExternoController.java
│   │   │       │   └── TrabajadorController.java
│   │   │       ├── viewcontroller/                  # Controladores de Vista
│   │   │       │   ├── MainViewController.java
│   │   │       │   ├── CrudEstudianteViewController.java
│   │   │       │   ├── CrudExternoViewController.java
│   │   │       │   └── CrudTrabajadorViewController.java
│   │   │       ├── factory/                         # Factory Pattern
│   │   │       │   └── ModelFactory.java
│   │   │       └── utils/                           # Utilidades
│   │   │           └── DataUtil.java
│   │   └── resources/                               # Recursos FXML
│   │       └── co/edu/uniquindio/universidad/
│   │           ├── main-view.fxml                   # Vista principal
│   │           ├── crud-estudiante.fxml
│   │           ├── crud-externo.fxml
│   │           └── crud-trabajador.fxml
│   └── test/                                        # Tests Unitarios
│       └── java/
│           └── co/edu/uniquindio/universidad/
│               ├── model/                           # Tests de Modelo
│               │   ├── UniversidadTest.java
│               │   └── EstudianteTest.java
│               ├── controller/                      # Tests de Controllers
│               │   ├── EstudianteControllerTest.java
│               │   ├── ExternoControllerTest.java
│               │   └── TrabajadorControllerTest.java
│               └── factory/                         # Tests de Factory
│                   └── ModelFactoryTest.java
```

## 🎨 Patrones de Diseño Implementados

### 1. MVC (Model-View-Controller)
- **Model**: Clases de dominio (`Persona`, `Estudiante`, `Externo`, `Trabajador`, `Universidad`)
- **View**: Archivos FXML que definen la interfaz gráfica
- **Controller**: Lógica de negocio y controladores de vista

### 2. Singleton Pattern
Implementado en `ModelFactory.java` para garantizar una única instancia del modelo de datos.

### 3. Factory Pattern
`ModelFactory` centraliza la creación y gestión de entidades del dominio.

### 4. Observer Pattern
Implementado mediante listeners en JavaFX para actualización reactiva de la UI.

## 🚀 Requisitos Previos

- JDK 22 o superior
- Maven 3.8+ (o usar Maven Wrapper)
- Sistema operativo compatible con JavaFX

## ▶️ Ejecución


### Desde IDE (IntelliJ IDEA, Eclipse, etc.):
1. Importar el proyecto como proyecto Maven
2. Ejecutar la clase `UniversidadApp.java`

## 🎯 Características Principales

### Gestión de Estudiantes
- Crear, editar, eliminar y consultar estudiantes
- Campos: nombre, apellido, identificación, edad, teléfono, carrera, semestre, promedio, código
- Validación de campos obligatorios
- Prevención de duplicados por identificación

### Gestión de Externos
- CRUD completo de trabajadores externos
- Campos: nombre, apellido, identificación, edad, teléfono, empresa, proyecto asignado, fecha de ingreso
- Validación de formato de fecha (YYYY-MM-DD)

### Gestión de Trabajadores
- CRUD completo de personal universitario
- Campos: nombre, apellido, identificación, edad, teléfono, departamento, cargo, salario, fecha de contratación
- Validación de datos numéricos y fechas

## 📊 Datos de Prueba

El sistema se inicializa automáticamente con datos de ejemplo:

- **3 Estudiantes** de diferentes carreras
- **2 Externos** con proyectos asignados
- **2 Trabajadores** de diferentes departamentos

## 🔄 Flujo de Trabajo

1. **Inicio**: La aplicación carga la vista principal con 3 tabs
2. **Nuevo**: Hacer clic en "Nuevo" para habilitar el formulario
3. **Crear**: Llenar los campos y hacer clic en "Agregar"
4. **Editar**: Seleccionar un registro de la tabla para editarlo
5. **Actualizar**: Modificar los campos y hacer clic en "Actualizar"
6. **Eliminar**: Seleccionar un registro y hacer clic en "Eliminar" (con confirmación)

## 📝 Notas Importantes

- El identificador único de cada persona es su **identificación** (cédula)
- Las fechas deben estar en formato **YYYY-MM-DD**
- Todos los campos son obligatorios
- La aplicación valida automáticamente los datos antes de guardar
- Los cambios se realizan en memoria (no hay persistencia en base de datos)

## 🔐 Validaciones Implementadas

- Campos obligatorios no vacíos
- Formato de fecha válido (ISO 8601)
- Conversión segura de tipos numéricos
- Prevención de duplicados
- Confirmación antes de eliminar

## 👥 Contribuciones

Este es un proyecto educativo que demuestra:
- Arquitectura MVC en JavaFX
- Implementación de patrones de diseño
- Desarrollo de aplicaciones de escritorio modernas
- Buenas prácticas de programación orientada a objetos

