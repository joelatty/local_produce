package au.com.team2media.localproduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LocalProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalProduceApplication.class, args);
    }
}
