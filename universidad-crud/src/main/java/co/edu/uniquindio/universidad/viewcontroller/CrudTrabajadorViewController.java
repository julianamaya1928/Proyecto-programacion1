package co.edu.uniquindio.universidad.viewcontroller;

import co.edu.uniquindio.universidad.controller.TrabajadorController;
import co.edu.uniquindio.universidad.model.Trabajador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controlador de la vista CRUD para trabajadores.
 * Gestiona la interfaz de usuario y delega la lógica de negocio al controller.
 */
public class CrudTrabajadorViewController {

    TrabajadorController trabajadorController;
    ObservableList<Trabajador> listaTrabajadores = FXCollections.observableArrayList();
    Trabajador trabajadorSeleccionado;

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
    private TableView<Trabajador> tableTrabajador;

    @FXML
    private TableColumn<Trabajador, String> tcApellido;

    @FXML
    private TableColumn<Trabajador, String> tcCargo;

    @FXML
    private TableColumn<Trabajador, String> tcDepartamento;

    @FXML
    private TableColumn<Trabajador, String> tcEdad;

    @FXML
    private TableColumn<Trabajador, String> tcFechaContratacion;

    @FXML
    private TableColumn<Trabajador, String> tcIdentificacion;

    @FXML
    private TableColumn<Trabajador, String> tcNombre;

    @FXML
    private TableColumn<Trabajador, String> tcSalario;

    @FXML
    private TableColumn<Trabajador, String> tcTelefono;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCargo;

    @FXML
    private TextField txtDepartamento;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtFechaContratacion;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtSalario;

    @FXML
    private TextField txtTelefono;

    @FXML
    void initialize() {
        trabajadorController = new TrabajadorController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerTrabajadores();
        tableTrabajador.setItems(listaTrabajadores);
        listenerSelection();
        deshabilitarCampos();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcDepartamento.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartamento()));
        tcCargo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCargo()));
        tcSalario.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalario())));
        tcFechaContratacion.setCellValueFactory(cellData -> {
            LocalDate fecha = cellData.getValue().getFechaContratacion();
            return new SimpleStringProperty(fecha != null ? fecha.toString() : "");
        });
    }

    private void obtenerTrabajadores() {
        listaTrabajadores.addAll(trabajadorController.obtenerTrabajadores());
    }

    private void listenerSelection() {
        tableTrabajador.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            trabajadorSeleccionado = newSelection;
            if (trabajadorSeleccionado != null) {
                mostrarInformacion(trabajadorSeleccionado);
                habilitarCampos();
                btnAgregar.setDisable(true);
                btnActualizar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void mostrarInformacion(Trabajador trabajador) {
        if (trabajador != null) {
            txtNombre.setText(trabajador.getNombre());
            txtApellido.setText(trabajador.getApellido());
            txtIdentificacion.setText(trabajador.getIdentificacion());
            txtEdad.setText(String.valueOf(trabajador.getEdad()));
            txtTelefono.setText(trabajador.getTelefono());
            txtDepartamento.setText(trabajador.getDepartamento());
            txtCargo.setText(trabajador.getCargo());
            txtSalario.setText(String.valueOf(trabajador.getSalario()));
            if (trabajador.getFechaContratacion() != null) {
                txtFechaContratacion.setText(trabajador.getFechaContratacion().toString());
            }
        }
    }

    @FXML
    void onActionActualizar(ActionEvent event) {
        if (trabajadorSeleccionado != null) {
            actualizarTrabajador();
        }
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearTrabajador();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        if (trabajadorSeleccionado != null) {
            eliminarTrabajador();
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
        tableTrabajador.getSelectionModel().clearSelection();
    }

    private void crearTrabajador() {
        if (validarCampos()) {
            LocalDate fecha = parsearFecha(txtFechaContratacion.getText());
            if (fecha != null) {
                Trabajador trabajador = trabajadorController.crearTrabajador(
                        txtNombre.getText(), txtApellido.getText(), txtIdentificacion.getText(),
                        txtEdad.getText(), txtTelefono.getText(), txtDepartamento.getText(),
                        txtCargo.getText(), txtSalario.getText(), fecha);
                if (trabajador != null) {
                    listaTrabajadores.add(trabajador);
                    mostrarMensaje("Notificación", "Creación trabajador", "Trabajador creado exitosamente",
                            Alert.AlertType.INFORMATION);
                    limpiarCampos();
                    deshabilitarCampos();
                    btnAgregar.setDisable(true);
                } else {
                    mostrarMensaje("Notificación", "Creación trabajador", "Ya existe un trabajador con esta identificación",
                            Alert.AlertType.WARNING);
                }
            } else {
                mostrarMensaje("Notificación", "Validación", "Formato de fecha inválido. Use YYYY-MM-DD",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void actualizarTrabajador() {
        if (validarCampos()) {
            LocalDate fecha = parsearFecha(txtFechaContratacion.getText());
            if (fecha != null) {
                boolean resultado = trabajadorController.actualizarTrabajador(
                        txtIdentificacion.getText(), txtNombre.getText(), txtApellido.getText(),
                        txtEdad.getText(), txtTelefono.getText(), txtDepartamento.getText(),
                        txtCargo.getText(), txtSalario.getText(), fecha);
                if (resultado) {
                    int indice = listaTrabajadores.indexOf(trabajadorSeleccionado);
                    listaTrabajadores.set(indice, trabajadorSeleccionado);
                    mostrarMensaje("Notificación", "Actualización trabajador", "Trabajador actualizado exitosamente",
                            Alert.AlertType.INFORMATION);
                    limpiarCampos();
                    deshabilitarCampos();
                    tableTrabajador.getSelectionModel().clearSelection();
                } else {
                    mostrarMensaje("Notificación", "Actualización trabajador", "Error al actualizar el trabajador",
                            Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Notificación", "Validación", "Formato de fecha inválido. Use YYYY-MM-DD",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void eliminarTrabajador() {
        boolean confirmacion = mostrarMensajeConfirmacion("¿Está seguro que desea eliminar este trabajador?");
        if (confirmacion) {
            boolean resultado = trabajadorController.eliminarTrabajador(trabajadorSeleccionado.getIdentificacion());
            if (resultado) {
                listaTrabajadores.remove(trabajadorSeleccionado);
                mostrarMensaje("Notificación", "Eliminación trabajador", "Trabajador eliminado exitosamente",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                deshabilitarCampos();
                tableTrabajador.getSelectionModel().clearSelection();
            } else {
                mostrarMensaje("Notificación", "Eliminación trabajador", "Error al eliminar el trabajador",
                        Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() || txtEdad.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() || txtDepartamento.getText().isEmpty() ||
                txtCargo.getText().isEmpty() || txtSalario.getText().isEmpty() ||
                txtFechaContratacion.getText().isEmpty()) {
            mostrarMensaje("Notificación", "Validación", "Todos los campos son obligatorios",
                    Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private LocalDate parsearFecha(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            return null;
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtTelefono.clear();
        txtDepartamento.clear();
        txtCargo.clear();
        txtSalario.clear();
        txtFechaContratacion.clear();
        trabajadorSeleccionado = null;
    }

    private void habilitarCampos() {
        txtNombre.setDisable(false);
        txtApellido.setDisable(false);
        txtEdad.setDisable(false);
        txtTelefono.setDisable(false);
        txtDepartamento.setDisable(false);
        txtCargo.setDisable(false);
        txtSalario.setDisable(false);
        txtFechaContratacion.setDisable(false);
    }

    private void deshabilitarCampos() {
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtIdentificacion.setDisable(true);
        txtEdad.setDisable(true);
        txtTelefono.setDisable(true);
        txtDepartamento.setDisable(true);
        txtCargo.setDisable(true);
        txtSalario.setDisable(true);
        txtFechaContratacion.setDisable(true);
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

