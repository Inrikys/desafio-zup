package com.zup.comics.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zup.comics.entities.User;
import com.zup.comics.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;


	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Henrique Zuper", "henriquezuper@zup.com.br", "41770143890", Instant.parse("1995-08-11T00:00:00Z"));
		User u2 = new User(null, "Usuário Zuper", "usuariozuper@zup.com.br", "20894158007", Instant.parse("1999-02-20T00:00:00Z"));
	
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
