package com.sistema.tareas.controller;

import com.sistema.tareas.model.Tarea;
import com.sistema.tareas.service.TareaServicio;
import jakarta.persistence.PersistenceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Controller
public class IndexController implements Initializable {

    private static final Logger logger =
            LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TareaServicio tareaServicio;

    //para indicar que son componentes de la vista index.fxml
    @FXML
    private TableView<Tarea> tareaTable;

    @FXML
    private TableColumn<Tarea, Integer> idColumn;

    @FXML
    private TableColumn<Tarea, String> tareaColumn;

    @FXML
    private TableColumn<Tarea, String> responsableColumn;

    @FXML
    private TableColumn<Tarea, String> estatusColumn;

    //es una lista observable, esto quiere decir que si hay un cambio en la lista
    //no tengo que implementar la logica para recargar la vista con los nuevos datos
    private final ObservableList<Tarea> tareaList =
            FXCollections.observableArrayList();

    @FXML
    private TextField tareaTextField;

    @FXML
    private TextField responsableTextField;

    @FXML
    private TextField estatusTextField;

    @FXML
    private Button agregarButton;

    private Tarea tarea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarTareas();
    }

    private void configurarColumnas(){
        //se tiene que pasar el mismo nombre que en el modelo
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tareaColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        responsableColumn.setCellValueFactory(new
                PropertyValueFactory<>("responsable"));
        estatusColumn.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }

    private void listarTareas(){
        tareaList.clear();
        tareaList.addAll(tareaServicio.listarTareas());
        tareaTable.setItems(tareaList);
    }

    public void agregarTarea(){
        if(tareaTextField.getText().isEmpty()){
            mostrarMensaje("Error validación",
                    "Debe rellenar el campo tarea");
            tareaTextField.requestFocus();
            return;
        }
        if(responsableTextField.getText().isEmpty()){
            mostrarMensaje("Error validación",
                    "Debe rellenar el campo responsable");
            responsableTextField.requestFocus();
            return;
        }
        if(estatusTextField.getText().isEmpty()){
            mostrarMensaje("Error validación",
                    "Debe rellenar el campo estatus");
            estatusTextField.requestFocus();
            return;
        }
        boolean esNuevaTarea = (tarea == null);
        if(tarea == null){
            tarea = new Tarea();
        }
        tarea.setNombre(tareaTextField.getText().strip());
        tarea.setResponsable(responsableTextField.getText().strip());
        tarea.setEstatus(estatusTextField.getText().strip());
        try {
            tareaServicio.guardarTarea(tarea);
            if (!esNuevaTarea)
                mostrarMensaje("Información", "Tarea modificada");
            else
                mostrarMensaje("Información", "Tarea agregada");
        } catch (PersistenceException e) {
            mostrarMensaje("Error BD",
                    "Error al agregar tarea a la Base de datos");
            logger.error("Error al insertar objeto en la BD: {}", e.getMessage());
            logger.debug("Stacktrace", e);
        }
        limpiarFormulario();
        listarTareas();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public void limpiarFormulario() {
        tareaTextField.clear();
        responsableTextField.clear();
        estatusTextField.clear();
        agregarButton.setText("Agregar");
        tarea = null;
    }

    public void cargarFormulario(){
        tarea = tareaTable.getSelectionModel().getSelectedItem();
        if(tarea != null){
            tareaTextField.setText(tarea.getNombre());
            responsableTextField.setText(tarea.getResponsable());
            estatusTextField.setText(tarea.getEstatus());
            agregarButton.setText("Modificar");
        }
    }

    public void eliminarTarea() {
        if(tarea == null) {
            mostrarMensaje("Informacion",
                    "Debe seleccionar un elemento de la tabla");
            return;
        }
        try {
            tareaServicio.eliminarTarea(tarea);
            mostrarMensaje("Información", "Tarea eliminada");
        } catch (PersistenceException e) {
            mostrarMensaje("Error BD", "Error al eliminar tarea de la Base de datos");
            logger.error("Error al eliminar objeto en la BD: {}", e.getMessage());
            logger.debug("Stacktrace", e);
        }
        limpiarFormulario();
        listarTareas();
    }
}
