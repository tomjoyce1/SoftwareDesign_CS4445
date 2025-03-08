package factoriesTest;

import static org.junit.jupiter.api.Assertions.*;

import factories.StateFactory;
import org.junit.jupiter.api.Test;
import models.states.FlightState;
import models.states.InAirState;
import models.states.LandingModeState;
import models.states.OnRunwayState;

class StateFactoryTest {

    @Test
    void getState_returnsInAirState_whenStateNameIsInTheAir() {
        FlightState state = StateFactory.getState("In the air");
        assertInstanceOf(InAirState.class, state);
    }

    @Test
    void getState_returnsLandingModeState_whenStateNameIsLandingMode() {
        FlightState state = StateFactory.getState("Landing mode");
        assertInstanceOf(LandingModeState.class, state);
    }

    @Test
    void getState_returnsOnRunwayState_whenStateNameIsOnGroundRunway() {
        FlightState state = StateFactory.getState("On ground/Runway");
        assertInstanceOf(OnRunwayState.class, state);
    }

    @Test
    void getState_throwsIllegalArgumentException_whenStateNameIsUnknown() {
        assertThrows(IllegalArgumentException.class, () -> StateFactory.getState("Unknown state"));
    }

    @Test
    void constructor_throwsIllegalStateException_whenCalled() {
        assertThrows(IllegalStateException.class, StateFactory::new);
    }
}