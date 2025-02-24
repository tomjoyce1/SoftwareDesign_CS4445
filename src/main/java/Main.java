import controllers.SimulatorController;
import models.SimulatorModel;
import models.decorators.radardecorator.BasicRadarDisplay;
import views.SimulatorView;

public class Main {

    public static void main(String[] args) {
        SimulatorModel model = new SimulatorModel();
        SimulatorView view = new SimulatorView(new BasicRadarDisplay());
        SimulatorController controller = new SimulatorController(model, view);
        controller.startSimulation();
    }
}
