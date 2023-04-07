package my.component;

import my.annotation.Bean;
import my.inter.MyDao;

@Bean
public class SomeDao implements MyDao {
    public SomeDao() {
        System.out.println("Constructed SomeDao");
    }
}
