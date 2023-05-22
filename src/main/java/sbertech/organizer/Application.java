package sbertech.organizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sbertech.organizer.services.OrganizerService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final OrganizerService organizerService;

    @Autowired
    public Application(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        organizerService.run();
    }
}
