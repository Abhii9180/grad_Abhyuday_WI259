package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = { 
    DataSourceAutoConfiguration.class, 
    HibernateJpaAutoConfiguration.class 
})
public class SpringProject1Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringProject1Application.class, args);
    }
}