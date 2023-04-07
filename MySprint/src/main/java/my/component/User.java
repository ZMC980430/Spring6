package my.component;

import my.annotation.Bean;
import my.annotation.Di;
import my.inter.MyService;

@Bean
public class User {
    @Di
    private MyService myService;
    public User() {
        System.out.println("constructed User object!");
    }

    @Override
    public String toString() {
        return "User{" +
                "myService=" + myService +
                '}';
    }
}
