package ru.ikolpakoff.addLogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.ikolpakoff.logic.CurrentMeter;

public class CurrentMeterAdder extends Adder {

    private CurrentMeter currentMeter;

    public CurrentMeterAdder(TextField textField, ComboBox comboBox) {
        super(textField, comboBox);
        currentMeter = new CurrentMeter(name);
    }

    @Override
    public void addToScene() {
        ObservableList<CurrentMeter> list = FXCollections.observableArrayList();
        list.addAll(comboBox.getItems());
        if (list.contains(currentMeter)) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", name), ButtonType.OK).showAndWait();
        } else {
            list.add(currentMeter);
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
