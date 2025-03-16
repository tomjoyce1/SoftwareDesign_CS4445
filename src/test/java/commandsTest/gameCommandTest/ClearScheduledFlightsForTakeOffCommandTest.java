// package commandsTest.gameCommandTest;

// import static org.mockito.ArgumentMatchers.anyList;
// import static org.mockito.Mockito.*;

// import commands.Command;
// import commands.gamecommand.ClearScheduledFlightsForTakeOffCommand;
// import models.map.AirTrafficMap;
// import models.map.takeoff.FlightSimulator;
// import models.map.takeoff.ScheduledFlight;
// import models.collision.CollisionDetector;
// import models.flight.FlightInterface;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.MockedConstruction;
// import org.mockito.MockitoAnnotations;
// import views.SimulatorView;

// import java.util.ArrayList;
// import java.util.List;

// public class ClearScheduledFlightsForTakeOffCommandTest {

//     private AutoCloseable mocks;

//     @BeforeEach
//     public void setUp() {
//         mocks = MockitoAnnotations.openMocks(this);
//     }
    
//     @AfterEach
//     public void tearDown() throws Exception {
//         if (mocks != null) {
//             mocks.close();
//         }
//     }

//     @Test
//     public void testExecuteEmptyScheduledFlights() {
//         AirTrafficMap airTrafficMap = mock(AirTrafficMap.class);
//         SimulatorView view = mock(SimulatorView.class);
//         List<ScheduledFlight> scheduledFlights = new ArrayList<>();
        
//         Command command = new ClearScheduledFlightsForTakeOffCommand(airTrafficMap, scheduledFlights, view);
//         command.execute();
        
//         assert(scheduledFlights.isEmpty());
//     }

//     @Test
//     public void testExecuteWithImmediateArrival() {
//         AirTrafficMap airTrafficMap = mock(AirTrafficMap.class);
//         SimulatorView view = mock(SimulatorView.class);
//         List<ScheduledFlight> scheduledFlights = new ArrayList<>();

//         ScheduledFlight flight1 = mock(ScheduledFlight.class);
//         FlightStub flight = mock(FlightStub.class);
//         when(flight.takeOff()).thenReturn(true);
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(flight.getState()).thenReturn("InFlight");

//         when(flight1.getFlight()).thenReturn(flight);
//         when(flight1.getDestinationRow()).thenReturn(5);
//         when(flight1.getDestinationCol()).thenReturn(10);
//         scheduledFlights.add(flight1);

//         try (MockedConstruction<FlightSimulator> flightSimConstruction = 
//                 mockConstruction(FlightSimulator.class, (mock, context) -> {
//                     when(mock.updateScheduledFlights(anyList())).thenReturn(true);
//                 });
//              MockedConstruction<CollisionDetector> collisionConstruction = 
//                 mockConstruction(CollisionDetector.class, (mock, context) -> {
//                     when(mock.checkAndHandleCollisions(anyList())).thenReturn(false);
//                 })
//         ) {
//             Command command = new ClearScheduledFlightsForTakeOffCommand(airTrafficMap, scheduledFlights, view);
//             command.execute();
            
//             List<FlightSimulator> flightSimInstances = flightSimConstruction.constructed();
//             FlightSimulator flightSim = flightSimInstances.get(0);
//             verify(flightSim, times(1)).simulateLandingProcedure(flight, 5, 10);
            
//             assert(scheduledFlights.isEmpty());
//         }
//     }

//     @Test
//     public void testExecuteWithDelayedArrivalAndCrashRemoval() {
//         AirTrafficMap airTrafficMap = mock(AirTrafficMap.class);
//         SimulatorView view = mock(SimulatorView.class);
//         List<ScheduledFlight> scheduledFlights = new ArrayList<>();

//         ScheduledFlight flight1 = mock(ScheduledFlight.class);
//         FlightStub flightA = mock(FlightStub.class);
//         when(flightA.takeOff()).thenReturn(true);
//         when(flightA.getFlightNumber()).thenReturn("FL001");
//         when(flightA.getState()).thenReturn("InFlight", "Crashed");
//         when(flight1.getFlight()).thenReturn(flightA);
//         when(flight1.getDestinationRow()).thenReturn(2);
//         when(flight1.getDestinationCol()).thenReturn(3);
//         scheduledFlights.add(flight1);

//         ScheduledFlight flight2 = mock(ScheduledFlight.class);
//         FlightStub flightB = mock(FlightStub.class);
//         when(flightB.takeOff()).thenReturn(false);
//         when(flightB.getFlightNumber()).thenReturn("FL002");
//         when(flightB.getState()).thenReturn("InFlight");
//         when(flight2.getFlight()).thenReturn(flightB);
//         when(flight2.getDestinationRow()).thenReturn(7);
//         when(flight2.getDestinationCol()).thenReturn(8);
//         scheduledFlights.add(flight2);

//         try (MockedConstruction<FlightSimulator> flightSimConstruction = 
//                 mockConstruction(FlightSimulator.class, (mock, context) -> {
//                     when(mock.updateScheduledFlights(anyList())).thenReturn(false, true);
//                 });
//              MockedConstruction<CollisionDetector> collisionConstruction = 
//                 mockConstruction(CollisionDetector.class, (mock, context) -> {
//                     when(mock.checkAndHandleCollisions(anyList())).thenReturn(false);
//                 })
//         ) {
//             Command command = new ClearScheduledFlightsForTakeOffCommand(airTrafficMap, scheduledFlights, view);
//             command.execute();
            
//             List<FlightSimulator> flightSimInstances = flightSimConstruction.constructed();
//             FlightSimulator flightSim = flightSimInstances.get(0);
            
//             verify(flightSim, times(1)).simulateLandingProcedure(flightB, 7, 8);
            
//             assert(scheduledFlights.isEmpty());
//         }
//     }
    
//     private interface FlightStub extends FlightInterface {
//         boolean takeOff();
//         String getFlightNumber();
//         String getState();
//     }
// }