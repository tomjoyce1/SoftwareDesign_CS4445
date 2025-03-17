package main;

import controllers.SimulatorController;
import models.SimulatorModel;
import views.SimulatorView;

public class Main {

    public static void main(String[] args) {
        SimulatorModel model = new SimulatorModel();
        SimulatorView view = new SimulatorView();
        SimulatorController controller = new SimulatorController(model, view);
        controller.startSimulation();
    }
}