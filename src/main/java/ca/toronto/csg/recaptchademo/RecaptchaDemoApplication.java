package ca.toronto.csg.recaptchademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RecaptchaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecaptchaDemoApplication.class, args);
	}

}
