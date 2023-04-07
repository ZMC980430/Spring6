package com.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {
    @Autowired
    private MyService myService;

    @Override
    public String toString() {
        return "User{" +
                "myService=" + myService +
                '}';
    }

    public void add() {
        System.out.println("User add");
    }
}
