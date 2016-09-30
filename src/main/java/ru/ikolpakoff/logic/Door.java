package ru.ikolpakoff.logic;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "DOOR_COMPONENT")
    @Column(name = "COMPONENT_COUNT")
    @MapKeyJoinColumn(name = "COMPONENT_ID")
    private Map<Component, Integer> components;

    @Column(name = "HASH_CODE")
    private int hash;

    public Door() {
    }

    public Door(int number, CameraType cameraType, ProtectionDevice protectionDevice, CurrentMeter currentMeter) {
        this.number = number;
        this.cameraType = cameraType;
        this.protectionDevice = protectionDevice;
        this.currentMeter = currentMeter;
        components = new HashMap<>();
        hash = hashCode();
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

    public int getHash() {
        return hash;
    }

    public void setHash() {
        this.hash = hashCode();
    }

    @Override
    public int hashCode() {

        int hash = 0;

        hash += cameraType.getName().hashCode();

        if(protectionDevice != null)
        hash += 31*hash + protectionDevice.getName().hashCode();

        if(currentMeter != null)
        hash += 31*hash + currentMeter.getName().hashCode();

        if(components != null && components.size() !=0) {
            Set<Map.Entry<Component, Integer>> entrySet = components.entrySet();
            for(Map.Entry<Component, Integer> entry : entrySet) {
                hash += 31*hash + entry.getKey().hashCode()* entry.getValue();
            }
        }


        return hash;
    }
}
