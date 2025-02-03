import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class AutomatedTest {  
    
    //this is a demo test
    @Test
    void demoTest() {
        int result =  2+2;
        Assertions.assertEquals(4, result, "Failed Basic Math");
    }
}
