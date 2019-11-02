package myApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication   //аннотация для обозначения и запуска приложения
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}