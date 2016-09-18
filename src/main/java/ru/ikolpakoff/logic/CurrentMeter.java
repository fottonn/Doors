package ru.ikolpakoff.logic;

import javax.persistence.*;

@Entity
@Table(name = "CURRENT_METER")
public class CurrentMeter implements Comparable<CurrentMeter> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CURRENT_METER_NAME")
    private String name;

    public CurrentMeter() {
    }

    public CurrentMeter(String name) {
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
    public int compareTo(CurrentMeter o) {
        return getName().compareToIgnoreCase(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentMeter that = (CurrentMeter) o;
        return name.equalsIgnoreCase(that.name);
    }

}
