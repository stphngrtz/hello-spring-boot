package de.stphngrtz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    /**
     * @see <a href="https://spring.io/guides/gs/spring-boot/">Building an Application with Spring Boot</a>
     */
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanDefinitionNames);
            for (String beanDefinitionName : beanDefinitionNames) {
                System.out.println(beanDefinitionName);
            }
        };
    }
}
