package com.voyager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringbootTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTemplateApplication.class, args);
        String baseUrl = "http://localhost:8084";
        String swaggerUrl = "http://localhost:8084/doc.html#/home";
        System.out.println("server start successfully!!!" + baseUrl);
        System.out.println("Swagger URL: " + swaggerUrl);
    }

}
