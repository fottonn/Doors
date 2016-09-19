package ru.ikolpakoff.logic.dao;

import ru.ikolpakoff.controllers.MainWindowController;

public interface ElementsDAO {
    void save();
    void fill(MainWindowController controller);
}
