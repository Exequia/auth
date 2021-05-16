package are.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		final Logger log = LoggerFactory.getLogger(AuthApplication.class);

        log.info("Start AuthApplication ...");
		SpringApplication.run(AuthApplication.class, args);
        log.info("AuthApplication started");
	}

}
