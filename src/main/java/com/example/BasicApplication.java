package com.example;

import com.example.repository.ResolvedorRepository;
import java.util.stream.Stream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.model.Resolvedor;

@SpringBootApplication
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }
    
    @Bean
    ApplicationRunner init(ResolvedorRepository repository) {
        return args -> {
            Stream.of(
                    "João", 
                    "Maria", 
                    "Zeca", 
                    "Mario", 
                    "Gustavo", 
                    "Camila", 
                    "Pedro", 
                    "Juliana", 
                    "Gisele"
                    ).forEach(name -> {
                Resolvedor resolvedor = new Resolvedor();
                resolvedor.setNome(name);
                repository.save(resolvedor);
            });
            repository.findAll().forEach(System.out::println);
        };
    }
}
