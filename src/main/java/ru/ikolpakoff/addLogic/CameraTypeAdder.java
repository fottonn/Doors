package ru.ikolpakoff.addLogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.ikolpakoff.logic.CameraType;

public class CameraTypeAdder extends Adder {

    private CameraType cameraType;

    public CameraTypeAdder(TextField textField, ComboBox comboBox) {
        super(textField, comboBox);
        cameraType = new CameraType(name);
    }

    @Override
    public void addToScene() {
        ObservableList<CameraType> list = FXCollections.observableArrayList();
        list.addAll(comboBox.getItems());
        if (list.contains(cameraType)) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", name), ButtonType.OK).showAndWait();
        } else {
            list.add(cameraType);
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
