package my.component;

import my.annotation.Bean;
import my.annotation.Di;
import my.inter.MyDao;
import my.inter.MyService;

@Bean
public class SomeService implements MyService {
    @Di
    private MyDao myDao;

    @Override
    public String toString() {
        return "SomeService{" +
                "myDao=" + myDao +
                '}';
    }
}
