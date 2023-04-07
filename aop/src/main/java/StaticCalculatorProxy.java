import aop.interfaces.Calculator;

public class StaticCalculatorProxy {

    Calculator<Object> calculator;
    Class<?> aClass;

    public StaticCalculatorProxy(Calculator<Object> calculator) {
        this.calculator = calculator;
        aClass = calculator.getClass().getGenericSuperclass().getClass();
    }

    // separate specific function from log
    public Object add(Object a, Object b) {

        // 附加功能由代理类中的代理方法来实现
        System.out.println("[日志] add 方法开始了，参数是：" + a + "," + b);

        // 通过目标对象来实现核心业务逻辑
        Object addResult = calculator.add(a, b);

        System.out.println("[日志] add 方法结束了，结果是：" + addResult);

        return addResult;
    }

}
