package ru.ikolpakoff.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.ikolpakoff.addLogic.Adder;
import ru.ikolpakoff.addLogic.AdderFactory;

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


    /*getters & setters start*/

    public void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public TextField getComponentAddTextField() {
        return componentAddTextField;
    }

    public TextField getProtectionDeviceAddTextField() {
        return protectionDeviceAddTextField;
    }

    public TextField getCurrentMeterAddTextField() {
        return currentMeterAddTextField;
    }

    public TextField getCameraTypeAddTextField() {
        return cameraTypeAddTextField;
    }

    /*getters & setters end*/


    /*business logic*/

    public void elementAdd(ActionEvent actionEvent) {

        Button button;
        Adder adder;
        AdderFactory factory = new AdderFactory();
        Object object = actionEvent.getSource();

        if (object != null && object instanceof Button) button = (Button) object;
        else return;

        adder = factory.createAdder(button, this, mainWindowController);
        adder.addToScene();
        adder.addToBase();
    }


    //disabling_undisabling addButtons
    @FXML
    private void undisablingAddButton(KeyEvent keyEvent) {
        Object source = keyEvent.getSource();
        if (!(source instanceof TextField)) return;
        TextField textField = (TextField) source;
        if (!textField.getText().trim().equals("")) {
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
        if (keyEvent.getCode() != KeyCode.ENTER) return;
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

