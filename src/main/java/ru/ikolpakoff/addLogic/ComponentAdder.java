package ru.ikolpakoff.addLogic;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ru.ikolpakoff.controllers.MainWindowController;
import ru.ikolpakoff.logic.Component;
import ru.ikolpakoff.logic.ComponentsComparator;
import ru.ikolpakoff.logic.dao.ComponentDAO;

import java.util.*;

public class ComponentAdder extends Adder {

    Component component;

    public ComponentAdder(TextField textField, MainWindowController mainWindowController) {
        super(textField, mainWindowController);
        component = new Component(name);
    }

    @Override
    public void addToScene() {

        GridPane componentsGridPane = mainWindowController.getComponentsGridPain();
        ObservableList<Node> children = componentsGridPane.getChildren();
        ObservableList<RowConstraints> rows = componentsGridPane.getRowConstraints();

        TreeSet<Component> components = new TreeSet<>(new ComponentsComparator());

        for (Node node : children) {
            if ((node instanceof Label) && ((GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0) >= 3))
                components.add(new Component(((Label) node).getText()));
        }

        if (checkForName(name, components)) {
            new Alert(Alert.AlertType.WARNING, String.format("%s уже содержится в базе", name), ButtonType.OK).showAndWait();
        } else {
            components.add(component);
            deleteRows(rows, children);

            addRows(componentsGridPane, components);
        }

        mainWindowController.getCameraTypeComboBox().setValue(null);
        Event.fireEvent(mainWindowController.getCameraTypeComboBox(), new ActionEvent());

        textField.clear();

    }

    @Override
    public void addToBase() {
        new ComponentDAO(component).save();
    }

    private void deleteRows(ObservableList<RowConstraints> rows, ObservableList<Node> children) {
        List<Node> forRemoveList = new ArrayList<>();

        for (Node node : children) {
            if ((GridPane.getRowIndex(node) != null ? GridPane.getRowIndex(node) : 0) >= 3) {
                forRemoveList.add(node);
            }
        }
        children.removeAll(forRemoveList);
        forRemoveList.clear();
        rows.remove(3, rows.size());
    }

    private void addRows(GridPane gridPane, TreeSet<Component> components) {

        Component comp;

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPrefHeight(30);
        rowConstraints.setMaxHeight(rowConstraints.getPrefHeight());
        rowConstraints.setMinHeight(rowConstraints.getPrefHeight());
        rowConstraints.setVgrow(Priority.SOMETIMES);

        for (int i = 0; i < components.size(); i++) {
            gridPane.getRowConstraints().add(rowConstraints);
        }

        for (int i = 3; i < gridPane.getRowConstraints().size(); i++) {
            comp = components.pollFirst();
            gridPane.add(comp.getLabel(), 0, i);
            comp.getCheckBox().setOnAction(event -> mainWindowController.checkBoxFire(event));
            gridPane.add(comp.getCheckBox(), 1, i);
            gridPane.add(comp.getBigDecimalField(), 2, i);
        }
    }

    //if name contains in set, return true, otherwise - false
    private boolean checkForName(String name, Set<Component> set) {

        Iterator<Component> iterator = set.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getName().equalsIgnoreCase(name)) return true;
        }

        return false;
    }

}
