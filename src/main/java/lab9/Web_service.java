package lab9;

import lab9.model.Deed;
import lab9.repository.DeedRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Web_service {

    public static void main(String[] args) {
        SpringApplication.run(Web_service.class, args);
    }

    @Bean
    CommandLineRunner runner(DeedRepository repository)
    {
        return args -> {
            repository.save(new Deed("Learn java","IN_PROGRESS"));
            repository.save(new Deed("Create web-service","ALMOST_DONE"));
            repository.save(new Deed("SLeep well","DONE"));
        };
    }
}