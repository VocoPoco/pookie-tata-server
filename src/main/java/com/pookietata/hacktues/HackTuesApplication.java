package com.pookietata.hacktues;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pookietata.hacktues.security", "com.pookietata.hacktues"})
public class HackTuesApplication {
    public static void main(String[] args) {
        SpringApplication.run(HackTuesApplication.class, args);
    }
}