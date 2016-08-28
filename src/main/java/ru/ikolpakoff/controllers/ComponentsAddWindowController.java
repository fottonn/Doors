package ru.ikolpakoff.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.ikolpakoff.logic.CameraType;
import ru.ikolpakoff.logic.CurrentMeter;
import ru.ikolpakoff.logic.ProtectionDevice;

public class ComponentsAddWindowController {
    private MainWindowController mainWindowController;
    @FXML
    private TextField componentAddTextField;
    @FXML
    private TextField protectionDeviceAddTextField;
    @FXML
    private TextField currentMeterAddTextField;
    @FXML
    private TextField cameraTypeAddTextField;

    @FXML
    private Button cameraTypeAddButton;
    @FXML
    private Button currentMeterAddButton;
    @FXML
    private Button protectionDeviceAddButton;
    @FXML
    private Button componentAddButton;

    public void cameraTypeAdd(ActionEvent actionEvent) {
        if(cameraTypeAddTextField.getText().trim().matches("")) {
            new Alert(Alert.AlertType.WARNING, "Тип камеры не введен", ButtonType.OK).showAndWait();
        }
        else {
            new CameraType(cameraTypeAddTextField.getText()).addToScene(mainWindowController.getCameraTypeComboBox());
        }
        cameraTypeAddTextField.clear();
    }

    public void currentMeterAdd(ActionEvent actionEvent) {
        if(currentMeterAddTextField.getText().trim().matches("")) {
            new Alert(Alert.AlertType.WARNING, "Наименование счетчика не введено", ButtonType.OK).showAndWait();
        }
        else {
            new CurrentMeter(currentMeterAddTextField.getText()).addToScene(mainWindowController.getCurrentMeterComboBox());
        }
        currentMeterAddTextField.clear();
    }

    public void protectionDeviceAdd(ActionEvent actionEvent) {
        if(protectionDeviceAddTextField.getText().trim().matches("")) {
            new Alert(Alert.AlertType.WARNING, "Наименование устройства защиты не введено", ButtonType.OK).showAndWait();
        }
        else {
            new ProtectionDevice(protectionDeviceAddTextField.getText()).addToScene(mainWindowController.getProtectionDeviceComboBox());
        }
        protectionDeviceAddTextField.clear();
    }

    public void componentAdd(ActionEvent actionEvent) {
//        new ComponentAdding(mainWindowController).addToScene(componentAddTextField.getText());
        componentAddTextField.clear();
    }

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }


    //disabling_undisabling addButtons
    @FXML
    private void undisablingAddButton(KeyEvent keyEvent) {
        Object source = keyEvent.getSource();
        if (!(source instanceof TextField)) return;
        TextField textField = (TextField) source;
        if (!textField.getText().equals("")) {
            switch (textField.getId()) {
                case "cameraTypeAddTextField":
                    cameraTypeAddButton.setDisable(false);
                    break;
                case "currentMeterAddTextField":
                    currentMeterAddButton.setDisable(false);
                    break;
                case "protectionDeviceAddTextField":
                    protectionDeviceAddButton.setDisable(false);
                    break;
                case "componentAddTextField":
                    componentAddButton.setDisable(false);
                    break;
            }
        } else switch (textField.getId()) {
            case "cameraTypeAddTextField":
                cameraTypeAddButton.setDisable(true);
                break;
            case "currentMeterAddTextField":
                currentMeterAddButton.setDisable(true);
                break;
            case "protectionDeviceAddTextField":
                protectionDeviceAddButton.setDisable(true);
                break;
            case "componentAddTextField":
                componentAddButton.setDisable(true);
                break;
        }
    }

    //enter pressed
    @FXML
    private void enterPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() != KeyCode.ENTER) return;
        Object source = keyEvent.getSource();
        if (!(source instanceof TextField)) return;
        TextField textField = (TextField) source;
        switch (textField.getId()) {
            case "cameraTypeAddTextField":
                Event.fireEvent(cameraTypeAddButton, new ActionEvent());
                break;
            case "currentMeterAddTextField":
                Event.fireEvent(currentMeterAddButton, new ActionEvent());
                break;
            case "protectionDeviceAddTextField":
                Event.fireEvent(protectionDeviceAddButton, new ActionEvent());
                break;
            case "componentAddTextField":
                Event.fireEvent(componentAddButton, new ActionEvent());
                break;
        }
    }

}

