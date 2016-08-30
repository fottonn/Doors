package ru.ikolpakoff.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import ru.ikolpakoff.base.HSQLBaseHelper;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.CurrentMeter;
import ru.ikolpakoff.logic.ProtectionDevice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    private Stage rootStage;

    public Stage getRootStage() {
        return rootStage;
    }

    public void setRootStage(Stage rootStage) {
        this.rootStage = rootStage;
    }

    @FXML
    private ComboBox<ProtectionDevice> protectionDeviceComboBox;

    @FXML
    private ComboBox<CurrentMeter> currentMeterComboBox;
    @FXML
    private ComboBox<CameraType> cameraTypeComboBox;
    @FXML
    private CustomTextField searchField;
    @FXML
    private MenuItem menuClose;
    @FXML
    private MenuItem menuAdd;
    @FXML
    private GridPane componentsGridPain;

    public ComboBox<CurrentMeter> getCurrentMeterComboBox() {
        return currentMeterComboBox;
    }

    public ComboBox<ProtectionDevice> getProtectionDeviceComboBox() {
        return protectionDeviceComboBox;
    }

    public ComboBox<CameraType> getCameraTypeComboBox() {
        return cameraTypeComboBox;
    }

    public GridPane getComponentsGridPain() {
        return componentsGridPain;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setupClearButtonField(searchField);
        HSQLBaseHelper.baseInit();

    }


    //method add clear button into CustomTextField
    private void setupClearButtonField(CustomTextField customTextField) {
        try {
            Method m = TextFields.class.getDeclaredMethod("setupClearButtonField", TextField.class, ObjectProperty.class);
            m.setAccessible(true);
            m.invoke(null, customTextField, customTextField.rightProperty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this method provide close action from menu
    public void closeAction(ActionEvent actionEvent) {
        rootStage.close();
    }

    //this method provide opening window for add elements from menu
    public void addAction(ActionEvent actionEvent) {
        Parent root = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("/fxmls/ComponentsAddWindow.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("ComponentsAddWindow.fxml not found");
            e.printStackTrace();
        }

        ((ComponentsAddWindowController) loader.getController()).setMainWindowController(this);

        Stage componentsAddWindowStage = new Stage();
        componentsAddWindowStage.initModality(Modality.APPLICATION_MODAL);
        componentsAddWindowStage.setTitle("Добавить");

        componentsAddWindowStage.setScene(new Scene(root, 415, 120));
        componentsAddWindowStage.setResizable(false);
        componentsAddWindowStage.show();
    }

    private void fillComboBox(String tableName, ComboBox<String> combobox) {
        ResultSet rs;
        try {
            rs = HSQLBaseHelper.st.executeQuery("SELECT title FROM " + tableName);
            while (rs.next()) {
                combobox.getItems().add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
