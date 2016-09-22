package ru.ikolpakoff.logic;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "DOOR")
public class Door {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NUMBER")
    private int number;

    @OneToOne
    @JoinColumn(name = "CAMERA_TYPE_ID")
    private CameraType cameraType;

    @OneToOne
    @JoinColumn(name = "PROTECTION_DEVICE_ID")
    private ProtectionDevice protectionDevice;

    @OneToOne
    @JoinColumn(name = "CURRENT_METER_ID")
    private CurrentMeter currentMeter;

    @ElementCollection
    @CollectionTable(name = "DOOR_COMPONENT")
    @Column(name = "COMPONENT_COUNT")
    @MapKeyJoinColumn(name = "COMPONENT_ID")
    private Map<Component, Integer> components;

    public Door(int number, CameraType cameraType, ProtectionDevice protectionDevice, CurrentMeter currentMeter) {
        this.number = number;
        this.cameraType = cameraType;
        this.protectionDevice = protectionDevice;
        this.currentMeter = currentMeter;
        components = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public CameraType getCameraType() {
        return cameraType;
    }

    public void setCameraType(CameraType cameraType) {
        this.cameraType = cameraType;
    }

    public ProtectionDevice getProtectionDevice() {
        return protectionDevice;
    }

    public void setProtectionDevice(ProtectionDevice protectionDevice) {
        this.protectionDevice = protectionDevice;
    }

    public CurrentMeter getCurrentMeter() {
        return currentMeter;
    }

    public void setCurrentMeter(CurrentMeter currentMeter) {
        this.currentMeter = currentMeter;
    }

    public Map<Component, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<Component, Integer> components) {
        this.components = components;
    }
}
