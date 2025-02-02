package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AutomatedTest {
    
    @Test
    void demoTest() {
        int result =  2+2;
        assertEquals(5, result, "Failed Basic Math"); ;
    }

    // @Test
    // void testFuelConsumptionWorking() {
    //     
    // }
}
