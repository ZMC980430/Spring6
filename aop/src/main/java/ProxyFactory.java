import aop.components.IntCalculator;
import aop.interfaces.Calculator;
import aop.interfaces.Calculator1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class ProxyFactory {

    public static Object getProxy(Object target) {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            Object result = null;
            try {
                System.out.println("[动态代理][日志] "+method.getName()+"，参数："+ Arrays.toString(args));
                result = method.invoke(target, args);
                System.out.println("[动态代理][日志] "+method.getName()+"，结果："+ result);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("[动态代理][日志] "+method.getName()+"，异常："+e.getMessage());
            } finally {
                System.out.println("[动态代理][日志] "+method.getName()+"，方法执行完毕");
            }
            return result;
        };
        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

    public static void main(String[] args) {
//        Calculator1 calculator = new IntCalculator();
//        Object proxy = ProxyFactory.getProxy(calculator);
//        Calculator1 proxy1 = (Calculator1) proxy;
//        System.out.println(proxy1.add(1, 2));

    }
}
