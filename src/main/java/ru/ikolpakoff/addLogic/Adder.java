package ru.ikolpakoff.addLogic;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.ikolpakoff.controllers.MainWindowController;

abstract public class Adder implements Adding {

    String name;
    ComboBox comboBox;
    TextField textField;
    MainWindowController mainWindowController;

    public Adder(TextField textField, ComboBox comboBox) {
        this.comboBox = comboBox;
        this.textField = textField;
        this.name = textField.getText().trim();
    }

    public Adder(TextField textField, MainWindowController mainWindowController) {
        this.textField = textField;
        this.mainWindowController = mainWindowController;
        this.name = textField.getText().trim();
    }


}
