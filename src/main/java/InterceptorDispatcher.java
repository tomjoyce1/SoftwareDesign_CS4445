import java.util.List;
import java.util.ArrayList;

public class InterceptorDispatcher{
    // manages interceptors
    private List<Interceptor> interceptorList = new ArrayList<>();

    public void addInterceptor(Interceptor i){
        interceptorList.add(i);
    }

    public void dispatch(String input){
        for(Interceptor i : interceptorList){
            i.handleRequest(input);
        }

    }


}