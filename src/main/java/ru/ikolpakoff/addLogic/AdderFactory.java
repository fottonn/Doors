package ru.ikolpakoff.addLogic;

import javafx.scene.control.Button;
import ru.ikolpakoff.controllers.ComponentsAddWindowController;
import ru.ikolpakoff.controllers.MainWindowController;

public class AdderFactory {

    ComponentsAddWindowController componentsAddWindowController;
    MainWindowController mainWindowController;
    Adder adder = null;

    public Adder createAdder(Button button, ComponentsAddWindowController componentsAddWindowController,
                              MainWindowController mainWindowController) {

        this.componentsAddWindowController = componentsAddWindowController;
        this.mainWindowController = mainWindowController;

        switch (button.getId()) {
            case "cameraTypeAddButton":
                adder = new CameraTypeAdder(componentsAddWindowController.getCameraTypeAddTextField(),
                        mainWindowController.getCameraTypeComboBox());
                break;
            case "currentMeterAddButton":
                adder = new CurrentMeterAdder(componentsAddWindowController.getCurrentMeterAddTextField(),
                        mainWindowController.getCurrentMeterComboBox());
                break;
            case "protectionDeviceAddButton":
                adder = new ProtectionDeviceAdder(componentsAddWindowController.getProtectionDeviceAddTextField(),
                        mainWindowController.getProtectionDeviceComboBox());
                break;
        }

        return adder;
    }
}
