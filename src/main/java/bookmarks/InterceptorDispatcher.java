package bookmarks;

import java.util.List;
import java.util.ArrayList;

public class InterceptorDispatcher {
    private final List<Interceptor> interceptorList = new ArrayList<>();

    public void addInterceptor(Interceptor i) {
        interceptorList.add(i);
    }

    public void dispatch(String input) {
        for (Interceptor i : interceptorList) {
            i.handleRequest(input);
        }
    }
}