package me.jaeseong.javatest.extension;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class ExtensionTest implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static  final long THRESHOLD = 1000L;

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        ExtensionContext.Store store= getStore(extensionContext);
        store.put("START_TIME",System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {

        Method requiredMethod = extensionContext.getRequiredTestMethod();
        SlowTest annotation = requiredMethod.getAnnotation(SlowTest.class);

        String testMethodname = extensionContext.getRequiredTestMethod().getName();

        ExtensionContext.Store store= getStore(extensionContext);
        long start_time =  store.get("START_TIME",Long.class);
        long duration =  System.currentTimeMillis() - start_time;
        if(duration > THRESHOLD && annotation == null){
            System.out.printf("Please consider mark method [%s] with @SlowTest.\n", testMethodname);
        }

    }

    private ExtensionContext.Store getStore(ExtensionContext context){
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodname = context.getRequiredTestMethod().getName();
        return context.getStore(ExtensionContext.Namespace.create(testClassName,testMethodname));
    }


}
