package aop.test;

import aop.components.IntCalculator;
import aop.components.SomeClass;
import aop.interfaces.Calculator;
import aop.interfaces.Calculator1;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Calculator1 calculator = context.getBean(Calculator1.class);
        calculator.nothing();
    }

}
