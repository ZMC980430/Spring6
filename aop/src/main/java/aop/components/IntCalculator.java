package aop.components;

import aop.interfaces.Calculator;
import aop.interfaces.Calculator1;
import org.springframework.stereotype.Component;

@Component
public class IntCalculator implements Calculator1 {
    @Override
    public void nothing() {
        System.out.println("nothing");
    }

    @Override
    public Integer add(Integer a, Integer b) {
        return a+b;
    }

    @Override
    public Integer minus(Integer a, Integer b) {
        return a-b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a*b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return a/b;
    }
}
