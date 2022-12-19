package application.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class ApplicationInitializer {
    public static void main(String[] args) {
        System.out.println("start");
        SpringApplication.run(ApplicationRunner.class, args);
    }
}
