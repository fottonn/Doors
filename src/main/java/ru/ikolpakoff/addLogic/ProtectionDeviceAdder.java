package ru.ikolpakoff.addLogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.ikolpakoff.logic.ProtectionDevice;

public class ProtectionDeviceAdder extends Adder {

    private ProtectionDevice protectionDevice;

    public ProtectionDeviceAdder(TextField textField, ComboBox comboBox) {
        super(textField, comboBox);
        protectionDevice = new ProtectionDevice(name);
    }

    @Override
    public void addToScene() {
        ObservableList<ProtectionDevice> list = FXCollections.observableArrayList();
        list.addAll(comboBox.getItems());
        if (list.contains(protectionDevice)) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", name), ButtonType.OK).showAndWait();
        } else {
            list.add(protectionDevice);
            FXCollections.sort(list);
            comboBox.getItems().clear();
            comboBox.getItems().addAll(list);
        }

        textField.clear();

    }

    @Override
    public void addToBase() {

    }
}
