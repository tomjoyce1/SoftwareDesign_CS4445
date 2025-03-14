package commands.gamecommand;

import commands.Command;
import models.map.AirTrafficMap;

public class ViewMapCommand implements Command{
    private final AirTrafficMap airTrafficMap;

    public ViewMapCommand(AirTrafficMap airTrafficMap) {
        this.airTrafficMap = airTrafficMap;
    }

    @Override
    public void execute() {
        airTrafficMap.printMap();
    }
    
}
