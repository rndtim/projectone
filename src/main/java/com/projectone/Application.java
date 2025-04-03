package com.projectone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        uncomment to use dotenv file
//        Dotenv dotenv = Dotenv.load();
//        dotenv.entries().forEach(entry -> {
//            System.setProperty(entry.getKey(), entry.getValue());
//        });
        SpringApplication.run(Application.class, args);
    }

}
