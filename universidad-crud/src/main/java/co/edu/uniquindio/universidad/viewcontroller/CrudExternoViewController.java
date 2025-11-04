package co.edu.uniquindio.universidad.viewcontroller;

import co.edu.uniquindio.universidad.controller.ExternoController;
import co.edu.uniquindio.universidad.model.Externo;
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
 * Controlador de la vista CRUD para externos.
 * Gestiona la interfaz de usuario y delega la lógica de negocio al controller.
 */
public class CrudExternoViewController {

    ExternoController externoController;
    ObservableList<Externo> listaExternos = FXCollections.observableArrayList();
    Externo externoSeleccionado;

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
    private TableView<Externo> tableExterno;

    @FXML
    private TableColumn<Externo, String> tcApellido;

    @FXML
    private TableColumn<Externo, String> tcEmpresa;

    @FXML
    private TableColumn<Externo, String> tcEdad;

    @FXML
    private TableColumn<Externo, String> tcFechaIngreso;

    @FXML
    private TableColumn<Externo, String> tcIdentificacion;

    @FXML
    private TableColumn<Externo, String> tcNombre;

    @FXML
    private TableColumn<Externo, String> tcProyectoAsignado;

    @FXML
    private TableColumn<Externo, String> tcTelefono;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtEmpresa;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtFechaIngreso;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtProyectoAsignado;

    @FXML
    private TextField txtTelefono;

    @FXML
    void initialize() {
        externoController = new ExternoController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerExternos();
        tableExterno.setItems(listaExternos);
        listenerSelection();
        deshabilitarCampos();
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        tcIdentificacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        tcEdad.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getEdad())));
        tcTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        tcEmpresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpresa()));
        tcProyectoAsignado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProyectoAsignado()));
        tcFechaIngreso.setCellValueFactory(cellData -> {
            LocalDate fecha = cellData.getValue().getFechaIngreso();
            return new SimpleStringProperty(fecha != null ? fecha.toString() : "");
        });
    }

    private void obtenerExternos() {
        listaExternos.addAll(externoController.obtenerExternos());
    }

    private void listenerSelection() {
        tableExterno.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            externoSeleccionado = newSelection;
            if (externoSeleccionado != null) {
                mostrarInformacion(externoSeleccionado);
                habilitarCampos();
                btnAgregar.setDisable(true);
                btnActualizar.setDisable(false);
                btnEliminar.setDisable(false);
            }
        });
    }

    private void mostrarInformacion(Externo externo) {
        if (externo != null) {
            txtNombre.setText(externo.getNombre());
            txtApellido.setText(externo.getApellido());
            txtIdentificacion.setText(externo.getIdentificacion());
            txtEdad.setText(String.valueOf(externo.getEdad()));
            txtTelefono.setText(externo.getTelefono());
            txtEmpresa.setText(externo.getEmpresa());
            txtProyectoAsignado.setText(externo.getProyectoAsignado());
            if (externo.getFechaIngreso() != null) {
                txtFechaIngreso.setText(externo.getFechaIngreso().toString());
            }
        }
    }

    @FXML
    void onActionActualizar(ActionEvent event) {
        if (externoSeleccionado != null) {
            actualizarExterno();
        }
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearExterno();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        if (externoSeleccionado != null) {
            eliminarExterno();
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
        tableExterno.getSelectionModel().clearSelection();
    }

    private void crearExterno() {
        if (validarCampos()) {
            LocalDate fecha = parsearFecha(txtFechaIngreso.getText());
            if (fecha != null) {
                Externo externo = externoController.crearExterno(
                        txtNombre.getText(), txtApellido.getText(), txtIdentificacion.getText(),
                        txtEdad.getText(), txtTelefono.getText(), txtEmpresa.getText(),
                        txtProyectoAsignado.getText(), fecha);
                if (externo != null) {
                    listaExternos.add(externo);
                    mostrarMensaje("Notificación", "Creación externo", "Externo creado exitosamente",
                            Alert.AlertType.INFORMATION);
                    limpiarCampos();
                    deshabilitarCampos();
                    btnAgregar.setDisable(true);
                } else {
                    mostrarMensaje("Notificación", "Creación externo", "Ya existe un externo con esta identificación",
                            Alert.AlertType.WARNING);
                }
            } else {
                mostrarMensaje("Notificación", "Validación", "Formato de fecha inválido. Use YYYY-MM-DD",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void actualizarExterno() {
        if (validarCampos()) {
            LocalDate fecha = parsearFecha(txtFechaIngreso.getText());
            if (fecha != null) {
                boolean resultado = externoController.actualizarExterno(
                        txtIdentificacion.getText(), txtNombre.getText(), txtApellido.getText(),
                        txtEdad.getText(), txtTelefono.getText(), txtEmpresa.getText(),
                        txtProyectoAsignado.getText(), fecha);
                if (resultado) {
                    int indice = listaExternos.indexOf(externoSeleccionado);
                    listaExternos.set(indice, externoSeleccionado);
                    mostrarMensaje("Notificación", "Actualización externo", "Externo actualizado exitosamente",
                            Alert.AlertType.INFORMATION);
                    limpiarCampos();
                    deshabilitarCampos();
                    tableExterno.getSelectionModel().clearSelection();
                } else {
                    mostrarMensaje("Notificación", "Actualización externo", "Error al actualizar el externo",
                            Alert.AlertType.ERROR);
                }
            } else {
                mostrarMensaje("Notificación", "Validación", "Formato de fecha inválido. Use YYYY-MM-DD",
                        Alert.AlertType.WARNING);
            }
        }
    }

    private void eliminarExterno() {
        boolean confirmacion = mostrarMensajeConfirmacion("¿Está seguro que desea eliminar este externo?");
        if (confirmacion) {
            boolean resultado = externoController.eliminarExterno(externoSeleccionado.getIdentificacion());
            if (resultado) {
                listaExternos.remove(externoSeleccionado);
                mostrarMensaje("Notificación", "Eliminación externo", "Externo eliminado exitosamente",
                        Alert.AlertType.INFORMATION);
                limpiarCampos();
                deshabilitarCampos();
                tableExterno.getSelectionModel().clearSelection();
            } else {
                mostrarMensaje("Notificación", "Eliminación externo", "Error al eliminar el externo",
                        Alert.AlertType.ERROR);
            }
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtIdentificacion.getText().isEmpty() || txtEdad.getText().isEmpty() ||
                txtTelefono.getText().isEmpty() || txtEmpresa.getText().isEmpty() ||
                txtProyectoAsignado.getText().isEmpty() || txtFechaIngreso.getText().isEmpty()) {
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
        txtEmpresa.clear();
        txtProyectoAsignado.clear();
        txtFechaIngreso.clear();
        externoSeleccionado = null;
    }

    private void habilitarCampos() {
        txtNombre.setDisable(false);
        txtApellido.setDisable(false);
        txtEdad.setDisable(false);
        txtTelefono.setDisable(false);
        txtEmpresa.setDisable(false);
        txtProyectoAsignado.setDisable(false);
        txtFechaIngreso.setDisable(false);
    }

    private void deshabilitarCampos() {
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtIdentificacion.setDisable(true);
        txtEdad.setDisable(true);
        txtTelefono.setDisable(true);
        txtEmpresa.setDisable(true);
        txtProyectoAsignado.setDisable(true);
        txtFechaIngreso.setDisable(true);
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

