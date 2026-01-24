package com.sanjeeban.CoreApartmentService;

import com.sanjeeban.CoreApartmentService.entity.UserAccount;
import com.sanjeeban.CoreApartmentService.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class CoreApartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApartmentServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(UserAccountRepository userRepository,
								   PasswordEncoder passwordEncoder) {
		return args -> {
				UserAccount admin = new UserAccount();
				admin.setUserId(1L); // Or use auto-generated ID
				admin.setName("System Admin");
				admin.setEmail("admin@example.com");
				admin.setMobile("9999999999");
				admin.setUserName("admin");
				admin.setPassword(passwordEncoder.encode("admin123")); // ✅ Encoded!
				admin.setStatus("ACTIVE");

				userRepository.save(admin);
				System.out.println("✅ Default admin created - Username: admin, Password: admin123");

		};
	}

}
