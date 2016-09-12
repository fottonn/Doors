package ru.ikolpakoff.logic;

public class ProtectionDevice implements Comparable<ProtectionDevice> {

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
