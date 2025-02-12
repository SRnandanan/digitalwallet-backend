package com.os.digitalwallet;

import com.os.digitalwallet.models.User;
import com.os.digitalwallet.repo.UserRepository;
import com.os.digitalwallet.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DigitalwalletApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DigitalwalletApplication.class, args);


	}

}
