package aop.proxy;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    // 通知类型
    // 前置

    @Before(value = "execution(public * aop.interfaces.Calculator1.*(..))")
    public void before() {
        System.out.println("before");
    }

    // 后置
    @After(value = "execution(public * aop.interfaces.Calculator1.*(..))")
    public void afterImp(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object target = joinPoint.getTarget();
        System.out.println("after implementation");
        System.out.println(name);
        System.out.println(target.getClass());
    }

    // 返回
    @AfterReturning(value = "execution(public * aop.interfaces.Calculator1.*(..))", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("after returning");
        System.out.println(result);
    }

    // 异常
    @AfterThrowing(value = "execution(public * aop.interfaces.Calculator1.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        System.out.println("exception handled");
        ex.printStackTrace();
    }

    // 环绕
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        Object result = null;
        try {
            System.out.println("环绕通知-->目标对象方法执行之前");
            //目标对象（连接点）方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知-->目标对象方法返回值之后");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("环绕通知-->目标对象方法出现异常时");
        } finally {
            System.out.println("环绕通知-->目标对象方法执行完毕");
        }
        return result;
    }

    @Order(1)
    @Pointcut("execution(public * aop.interfaces.Calculator1.*(..))")
    public void pointCut(){}

//    @Before(value = "execution(public void aop.components.SomeClass.print(String))")
//    public void beforeTest() {
//        System.out.println("before");
//    }


}
