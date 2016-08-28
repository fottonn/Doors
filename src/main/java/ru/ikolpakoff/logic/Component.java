package ru.ikolpakoff.logic;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import jfxtras.labs.scene.control.BigDecimalField;

import java.math.BigDecimal;

public class Component {

    private String name;
    private Label label;
    private CheckBox checkBox;
    private BigDecimalField bigDecimalField;

    public Component() {}

    public Component(String name) {

        this.name = name;

        label = new Label(name);
        GridPane.setHalignment(label, HPos.LEFT);
        GridPane.setValignment(label, VPos.CENTER);
        GridPane.setMargin(label, new Insets(5));

        checkBox = new CheckBox();
        checkBox.setDisable(true);
        checkBox.setMnemonicParsing(false);
        GridPane.setHalignment(checkBox, HPos.CENTER);
        GridPane.setValignment(checkBox, VPos.CENTER);

        bigDecimalField = new BigDecimalField();
        bigDecimalField.getStylesheets().add("css/MyBigDecimalField.css");
        bigDecimalField.setDisable(true);
        bigDecimalField.setText("0");
        bigDecimalField.setMinValue(new BigDecimal(0));
        bigDecimalField.setPrefWidth(150);
        GridPane.setHalignment(bigDecimalField, HPos.CENTER);
        GridPane.setValignment(bigDecimalField, VPos.CENTER);
        GridPane.setMargin(bigDecimalField, new Insets(5));
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public BigDecimalField getBigDecimalField() {
        return bigDecimalField;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public void setBigDecimalField(BigDecimalField bigDecimalField) {
        this.bigDecimalField = bigDecimalField;
    }
}
