import Controllers.SimulatorController;
import Models.SimulatorModel;
import Models.Decorators.RadarDecorator.BasicRadarDisplay;
import Views.SimulatorView;

public class Main {
    
    public static void main(String[] args) {
        SimulatorModel model = new SimulatorModel();
        SimulatorView view = new SimulatorView(new BasicRadarDisplay());
        SimulatorController controller = new SimulatorController(model, view);
        controller.startSimulation();
    }
}
