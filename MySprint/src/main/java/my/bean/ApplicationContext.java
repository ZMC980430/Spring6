package my.bean;

public interface ApplicationContext {
    Object getBean(Class<?> c);
    Object getBean(String s) throws ClassNotFoundException;
}
