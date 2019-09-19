package com.nevernote.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NevernoteApplication {

    public static void main(String[] args) {
        //Sets up default config
        //Starts spring app context
        //Class path scan
        //Starts tomcat server
        SpringApplication.run(NevernoteApplication.class,args);
    }
}
