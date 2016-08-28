package ru.ikolpakoff.logic;

import java.util.Comparator;

public class ComponentsComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
