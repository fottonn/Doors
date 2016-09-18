package ru.ikolpakoff.logic;

import javax.persistence.*;

@Entity
@Table(name = "PROTECTION_DEVICE")
public class ProtectionDevice implements Comparable<ProtectionDevice> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PROTECTION_DEVICE_NAME")
    private String name;

    public ProtectionDevice() {
    }

    public ProtectionDevice(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
