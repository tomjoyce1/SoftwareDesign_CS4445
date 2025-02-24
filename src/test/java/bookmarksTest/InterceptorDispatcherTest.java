package bookmarksTest;

import bookmarks.Interceptor;
import bookmarks.InterceptorDispatcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

class InterceptorDispatcherTest {

    @Test
    void dispatchCallsHandleRequestOnSingleInterceptor() {
        Interceptor interceptor = Mockito.mock(Interceptor.class);
        InterceptorDispatcher dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(interceptor);
        dispatcher.dispatch("inputData");
        verify(interceptor).handleRequest("inputData");
    }

    @Test
    void dispatchCallsHandleRequestOnMultipleInterceptors() {
        Interceptor interceptor1 = Mockito.mock(Interceptor.class);
        Interceptor interceptor2 = Mockito.mock(Interceptor.class);
        InterceptorDispatcher dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(interceptor1);
        dispatcher.addInterceptor(interceptor2);
        dispatcher.dispatch("multiInput");
        verify(interceptor1).handleRequest("multiInput");
        verify(interceptor2).handleRequest("multiInput");
    }

    @Test
    void dispatchDoesNothingWhenNoInterceptorsAdded() {
        InterceptorDispatcher dispatcher = new InterceptorDispatcher();
        dispatcher.dispatch("noInterceptor");
    }

    @Test
    void addInterceptorStoresMultipleInterceptors() {
        Interceptor interceptor1 = Mockito.mock(Interceptor.class);
        Interceptor interceptor2 = Mockito.mock(Interceptor.class);
        InterceptorDispatcher dispatcher = new InterceptorDispatcher();
        dispatcher.addInterceptor(interceptor1);
        dispatcher.addInterceptor(interceptor2);
        dispatcher.dispatch("storeTest");
        verify(interceptor1).handleRequest("storeTest");
        verify(interceptor2).handleRequest("storeTest");
    }
}