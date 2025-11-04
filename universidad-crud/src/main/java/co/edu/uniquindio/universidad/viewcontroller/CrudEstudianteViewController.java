package co.edu.uniquindio.universidad.viewcontroller;

import co.edu.uniquindio.universidad.controller.EstudianteController;
import co.edu.uniquindio.universidad.model.Estudiante;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador de la vista CRUD para estudiantes.
 * Gestiona la interfaz de usuario y delega la lógica de negocio al controller.
 */
public class CrudEstudianteViewController {

    EstudianteController estudianteController;
    ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();
    Estudiante estudianteSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnNuevo;

    @FXML
    private TableView<Estudiante> tableEstudiante;

    @FXML
    private TableColumn<Estudiante, String> tcApellido;

    @FXML
    private TableColumn<Estudiante, String> tcCarrera;

    @FXML
    private TableColumn<Estudiante, String> tcCodigo;

    @FXML
    private TableColumn<Estudiante, String> tcEdad;

    @FXML
    private TableColumn<Estudiante, String> tcIdentificacion;

    @FXML
    private TableColumn<Estudiante, String> tcNombre;

    @FXML
    private TableColumn<Estudiante, String> tcPromedio;

    @FXML
    private TableColumn<Estudiante, String> tcSemestre;

    @FXML
    private TableColumn<Estudiante, String> tcTelefono;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCarrera;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPromedio;

    @FXML
    private TextField txtSemestre;

    @FXML
    private TextField txtTelefono;

    @FXML
    void initialize() {
        estudianteController = new EstudianteController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerEstudiantes();
        tableEstudiante.setItems(listaEstudiantes);
        listenerSelection();
        deshabilitarCampos();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcCarrera.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarrera()));
        tcSemestre.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSemestre())));
        tcPromedio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPromedio())));
        tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
    }

    private void obtenerEstudiantes() {
        listaEstudiantes.addAll(estudianteController.obtenerEstudiantes());
    }

    private void listenerSelection() {
        tableEstudiante.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            estudianteSeleccionado = newSelection;
            if (estudianteSeleccionado != null) {
                mostrarInformacion(estudianteSeleccionado);
                habilitarCampos();
                btnAgregar.setDisable(true);
                btnActualizar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void mostrarInformacion(Estudiante estudiante) {
        if (estudiante != null) {
            txtNombre.setText(estudiante.getNombre());
            txtApellido.setText(estudiante.getApellido());
            txtIdentificacion.setText(estudiante.getIdentificacion());
            txtEdad.setText(String.valueOf(estudiante.getEdad()));
            txtTelefono.setText(estudiante.getTelefono());
            txtCarrera.setText(estudiante.getCarrera());
            txtSemestre.setText(String.valueOf(estudiante.getSemestre()));
            txtPromedio.setText(String.valueOf(estudiante.getPromedio()));
            txtCodigo.setText(estudiante.getCodigo());
        }
    }

    @FXML
    void onActionActualizar(ActionEvent event) {
        if (estudianteSeleccionado != null) {
            actualizarEstudiante();
        }
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearEstudiante();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        if (estudianteSeleccionado != null) {
            eliminarEstudiante();
        }
    }

    @FXML
    void onActionNuevo(ActionEvent event) {
        limpiarCampos();
        habilitarCampos();
        txtIdentificacion.setDisable(false);
        btnAgregar.setDisable(false);
        btnActualizar.setDisable(true);
        btnEliminar.setDisable(true);
        tableEstudiante.getSelectionModel().clearSelection();
    }

    private void crearEstudiante() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String identificacion = txtIdentificacion.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();
        String carrera = txtCarrera.getText();
        String semestre = txtSemestre.getText();
        String promedio = txtPromedio.getText();
        String codigo = txtCodigo.getText();

        if (validarCampos()) {
            Estudiante estudiante = estudianteController.crearEstudiante(nombre, apellido, identificacion, edad,
                    telefono, carrera, semestre, promedio, codigo);
            if (estudiante != null) {
                listaEstudiantes.add(estudiante);
                mostrarMensaje("Notificación", "Creación estudiante", "Estudiante creado exitosamente",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                deshabilitarCampos();
                btnAgregar.setDisable(true);
            } else {
                mostrarMensaje("Notificación", "Creación estudiante", "Ya existe un estudiante con esta identificación",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void actualizarEstudiante() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String identificacion = txtIdentificacion.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();
        String carrera = txtCarrera.getText();
        String semestre = txtSemestre.getText();
        String promedio = txtPromedio.getText();
        String codigo = txtCodigo.getText();

        if (validarCampos()) {
            boolean resultado = estudianteController.actualizarEstudiante(identificacion, nombre, apellido, edad,
                    telefono, carrera, semestre, promedio, codigo);
            if (resultado) {
                int indice = listaEstudiantes.indexOf(estudianteSeleccionado);
                listaEstudiantes.set(indice, estudianteSeleccionado);
                mostrarMensaje("Notificación", "Actualización estudiante", "Estudiante actualizado exitosamente",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                deshabilitarCampos();
                tableEstudiante.getSelectionModel().clearSelection();
            } else {
                mostrarMensaje("Notificación", "Actualización estudiante", "Error al actualizar el estudiante",
                        Alert.AlertType.ERROR);
            }
        }
    }

    private void eliminarEstudiante() {
        boolean confirmacion = mostrarMensajeConfirmacion("¿Está seguro que desea eliminar este estudiante?");
        if (confirmacion) {
            boolean resultado = estudianteController.eliminarEstudiante(estudianteSeleccionado.getIdentificacion());
            if (resultado) {
                listaEstudiantes.remove(estudianteSeleccionado);
                mostrarMensaje("Notificación", "Eliminación estudiante", "Estudiante eliminado exitosamente",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                deshabilitarCampos();
                tableEstudiante.getSelectionModel().clearSelection();
            } else {
                mostrarMensaje("Notificación", "Eliminación estudiante", "Error al eliminar el estudiante",
                        Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() || txtEdad.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() || txtCarrera.getText().isEmpty() ||
                txtSemestre.getText().isEmpty() || txtPromedio.getText().isEmpty() ||
                txtCodigo.getText().isEmpty()) {
            mostrarMensaje("Notificación", "Validación", "Todos los campos son obligatorios",
                    Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtTelefono.clear();
        txtCarrera.clear();
        txtSemestre.clear();
        txtPromedio.clear();
        txtCodigo.clear();
        estudianteSeleccionado = null;
    }

    private void habilitarCampos() {
        txtNombre.setDisable(false);
        txtApellido.setDisable(false);
        txtEdad.setDisable(false);
        txtTelefono.setDisable(false);
        txtCarrera.setDisable(false);
        txtSemestre.setDisable(false);
        txtPromedio.setDisable(false);
        txtCodigo.setDisable(false);
    }

    private void deshabilitarCampos() {
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtIdentificacion.setDisable(true);
        txtEdad.setDisable(true);
        txtTelefono.setDisable(true);
        txtCarrera.setDisable(true);
        txtSemestre.setDisable(true);
        txtPromedio.setDisable(true);
        txtCodigo.setDisable(true);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        return action.isPresent() && action.get() == ButtonType.OK;
    }
}

