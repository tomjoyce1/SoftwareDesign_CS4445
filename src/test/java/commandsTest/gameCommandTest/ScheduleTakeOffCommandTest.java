// package commandsTest.gameCommandTest;

// import commands.gamecommand.ScheduleTakeOffCommand;
// import models.flight.IFlight;
// import models.map.AirTrafficMap;
// import models.map.takeoff.ScheduledFlight;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import views.SimulatorView;
// import utils.AirportSelectionUtil;

// import java.util.ArrayList;
// import java.util.List;

// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.Mockito.*;

// public class ScheduleTakeOffCommandTest {

//     @Mock
//     private IFlight flight;
//     @Mock
//     private AirTrafficMap airTrafficMap;
//     @Mock
//     private SimulatorView view;
//     @Mock
//     private ScheduledFlight scheduledFlight;

//     private AirportSelectionUtil utils = mock(AirportSelectionUtil.class);

//     private List<ScheduledFlight> scheduledFlights;
//     private ScheduleTakeOffCommand command;

//     @BeforeEach
//     public void setUp() {
//         MockitoAnnotations.openMocks(this);
//         scheduledFlights = new ArrayList<>();
//         command = new ScheduleTakeOffCommand(flight, airTrafficMap, view, scheduledFlights);
//     }

//     @Test
//     public void testExecuteFlightAlreadyScheduled() {
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(scheduledFlight.getFlight()).thenReturn(flight);
//         scheduledFlights.add(scheduledFlight);

//         command.execute();

//         verify(flight, times(1)).getFlightNumber();
//         verify(scheduledFlight, times(1)).getFlight();
//         verifyNoMoreInteractions(flight, airTrafficMap, view);
//     }

//     @Test
//     public void testExecuteFlightNotAtAirport() {
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(airTrafficMap.findFlightPosition(flight)).thenReturn(null);

//         command.execute();

//         verify(flight, times(1)).getFlightNumber();
//         verify(airTrafficMap, times(1)).findFlightPosition(flight);
//         verifyNoMoreInteractions(flight, airTrafficMap, view);
//     }

//     @Test
//     public void testExecuteDestinationSelectionCancelled() {
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(airTrafficMap.findFlightPosition(flight)).thenReturn(new int[]{0, 0});
//         when(utils.AirportSelectionUtil.selectAirport(airTrafficMap, view, 0, 0)).thenReturn(null);

//         command.execute();

//         verify(flight, times(1)).getFlightNumber();
//         verify(airTrafficMap, times(1)).findFlightPosition(flight);
//         verify(utils.AirportSelectionUtil, times(1)).selectAirport(airTrafficMap, view, 0, 0);
//         verifyNoMoreInteractions(flight, airTrafficMap, view);
//     }

//     @Test
//     public void testExecuteDestinationLocked() {
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(airTrafficMap.findFlightPosition(flight)).thenReturn(new int[]{0, 0});
//         when(utils.AirportSelectionUtil.selectAirport(airTrafficMap, view, 0, 0)).thenReturn(new int[]{1, 1});
//         when(airTrafficMap.getCell(1, 1)).thenReturn(mock(MapCell.class));
//         when(airTrafficMap.canPlaceFlightAt(flight, 1, 1)).thenReturn(false);

//         command.execute();

//         verify(flight, times(1)).getFlightNumber();
//         verify(airTrafficMap, times(1)).findFlightPosition(flight);
//         verify(utils.AirportSelectionUtil, times(1)).selectAirport(airTrafficMap, view, 0, 0);
//         verify(airTrafficMap, times(1)).getCell(1, 1);
//         verify(airTrafficMap, times(1)).canPlaceFlightAt(flight, 1, 1);
//         verifyNoMoreInteractions(flight, airTrafficMap, view);
//     }

//     @Test
//     public void testExecuteSuccessfulScheduling() {
//         when(flight.getFlightNumber()).thenReturn("FL123");
//         when(airTrafficMap.findFlightPosition(flight)).thenReturn(new int[]{0, 0});
//         when(utils.AirportSelectionUtil.selectAirport(airTrafficMap, view, 0, 0)).thenReturn(new int[]{1, 1});
//         MapCell destinationCell = mock(MapCell.class);
//         when(airTrafficMap.getCell(1, 1)).thenReturn(destinationCell);
//         when(destinationCell.getAirportLabel()).thenReturn("Destination Airport");
//         when(airTrafficMap.canPlaceFlightAt(flight, 1, 1)).thenReturn(true);

//         command.execute();

//         verify(flight, times(1)).getFlightNumber();
//         verify(airTrafficMap, times(1)).findFlightPosition(flight);
//         verify(utils.AirportSelectionUtil, times(1)).selectAirport(airTrafficMap, view, 0, 0);
//         verify(airTrafficMap, times(1)).getCell(1, 1);
//         verify(destinationCell, times(1)).getAirportLabel();
//         verify(airTrafficMap, times(1)).canPlaceFlightAt(flight, 1, 1);
//         assertEquals(1, scheduledFlights.size());
//         verifyNoMoreInteractions(flight, airTrafficMap, view);
//     }
// }