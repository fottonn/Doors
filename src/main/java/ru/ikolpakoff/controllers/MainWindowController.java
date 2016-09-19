package ru.ikolpakoff.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.BigDecimalField;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import ru.ikolpakoff.base.HibernateUtil;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.CurrentMeter;
import ru.ikolpakoff.logic.ProtectionDevice;
import ru.ikolpakoff.logic.dao.CameraTypeDAO;
import ru.ikolpakoff.logic.dao.ComponentDAO;
import ru.ikolpakoff.logic.dao.CurrentMeterDAO;
import ru.ikolpakoff.logic.dao.ProtectionDeviceDAO;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


public class MainWindowController implements Initializable {

    private Stage rootStage;
    public HibernateUtil hibernateUtil;

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
        hibernateUtil = HibernateUtil.getHibernateUtil();
        new CameraTypeDAO().fill(this);
        new ProtectionDeviceDAO().fill(this);
        new CurrentMeterDAO().fill(this);
        new ComponentDAO().fill(this);


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
        if (HibernateUtil.sessionFactory != null) {
            HibernateUtil.sessionFactoryClose();
        }
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

    //this method provide undisabling checkboxes
    public void undisablingCheckBoxes() {
        ObservableList<Node> children = componentsGridPain.getChildren();
        if (cameraTypeComboBox.getValue() != null) {
            for (Node node : children) {
                if (node instanceof CheckBox) {
                    node.setDisable(false);
                }
            }
        } else {
            for (Node node : children) {
                if (node instanceof CheckBox) {
                    if (((CheckBox) node).isSelected()) {
                        ((CheckBox) node).setSelected(false);
                        Event.fireEvent(node, new ActionEvent());
                    }
                    node.setDisable(true);
                }
            }
        }
    }

    //this method provide undisabling or disabling ComboBoxes or BigDecimalFields of elements
    public void checkBoxFire(ActionEvent actionEvent) {
        CheckBox cb = (CheckBox) actionEvent.getSource();
        int rIndex = GridPane.getRowIndex(cb);
        for (Node node : componentsGridPain.getChildren()) {
            if (node instanceof ComboBox || node instanceof BigDecimalField) {
                if ((GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0) == rIndex) {
                    if (cb.isSelected()) node.setDisable(false);
                    else {
                        if (node instanceof ComboBox) ((ComboBox) node).setValue(null);
                        else ((BigDecimalField) node).setNumber(new BigDecimal(0));
                        node.setDisable(true);
                    }
                }
            }
        }

    }

}
