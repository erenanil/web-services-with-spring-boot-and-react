package com.projectX.webService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projectX.webService.user.User;
import com.projectX.webService.user.UserRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner userCreator(UserRepository userRepository) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				for (var i = 1; i <= 25; i++) {
					User user = new User();
					user.setUsername("user" + i);
					user.setPassword(passwordEncoder.encode("P4ssword"));
					user.setEmail("user" + i + "@mail.com");
					user.setActive(true);
					userRepository.save(user);
				}
			}

		};
	}
}
