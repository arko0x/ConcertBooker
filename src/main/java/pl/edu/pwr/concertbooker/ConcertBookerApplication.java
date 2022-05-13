package pl.edu.pwr.concertbooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class ConcertBookerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConcertBookerApplication.class, args);
    }

}
