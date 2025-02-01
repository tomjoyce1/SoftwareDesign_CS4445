import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingInterceptor implements Interceptor {

    private static Logger logger = Logger.getLogger("PlaneLog");
    private static FileHandler fh;

    static {
        try {
            fh = new FileHandler("../logOutput/logfileOutput.log", true); 
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleRequest(String input) {
        logger.info(input);
    }
}
