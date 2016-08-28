package ru.ikolpakoff.logic;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import jfxtras.labs.scene.control.BigDecimalField;
import ru.ikolpakoff.controllers.MainWindowController;

import java.util.*;

public class ComponentAdding {

    private MainWindowController mainWindowController;

    public ComponentAdding(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
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

    //if name contains in set, return true, otherwise - false
    private boolean checkForName(String name, Set<Component> set) {

        Iterator<Component> iterator = set.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getName().equalsIgnoreCase(name)) return true;
        }

        return false;
    }
}
