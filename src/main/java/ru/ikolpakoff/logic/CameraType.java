package ru.ikolpakoff.logic;

import javax.persistence.*;

@Entity
@Table(name = "CAMERA_TYPE")
public class CameraType implements Comparable<CameraType> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CAMERA_TYPE_NAME")
    private String name;

    public CameraType() {
    }

    public CameraType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(CameraType o) {
        return getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraType that = (CameraType) o;
        return name.equalsIgnoreCase(that.name);
    }

}
