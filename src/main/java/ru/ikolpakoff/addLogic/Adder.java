package ru.ikolpakoff.addLogic;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

abstract public class Adder implements Adding {

    String name;
    ComboBox comboBox;
    TextField textField;

    public Adder(TextField textField, ComboBox comboBox) {
        this.comboBox = comboBox;
        this.textField = textField;
        this.name = textField.getText();
    }


}
