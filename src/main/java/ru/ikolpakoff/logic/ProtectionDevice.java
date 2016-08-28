package ru.ikolpakoff.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

public class ProtectionDevice implements AddingToSceneAndToBase, Comparable<ProtectionDevice> {

    private String name;

    public ProtectionDevice() {
    }

    public ProtectionDevice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addToBase() {

    }

    @Override
    public void addToScene(ComboBox comboBox) {
        ObservableList<ProtectionDevice> list = FXCollections.observableArrayList();
        list.addAll(comboBox.getItems());
        if (list.contains(this)) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", name), ButtonType.OK).showAndWait();
        } else {
            addToBase();
            list.add(this);
            FXCollections.sort(list);
            comboBox.getItems().clear();
            comboBox.getItems().addAll(list);
        }

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(ProtectionDevice o) {
        return getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtectionDevice that = (ProtectionDevice) o;
        return name.equalsIgnoreCase(that.name);
    }

}
