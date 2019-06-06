package greet.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GreetProxy implements InvocationHandler {

    private GreetInterface obj;

    public static GreetInterface newInstance(GreetInterface obj) {
        return (GreetInterface) java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new GreetProxy(obj));
    }


    private GreetProxy(GreetInterface obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;

        try {
            if (method.getName().startsWith("greetPerson")) {
                obj.addToMap(args[0].toString());
            }
            result = method.invoke(obj, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
