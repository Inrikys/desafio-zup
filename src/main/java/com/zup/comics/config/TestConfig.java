package com.zup.comics.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zup.comics.entities.User;
import com.zup.comics.repositories.UserRepository;
import com.zup.comics.util.DateUtil;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User u1 = new User(null, "Henrique Zupper", "henriquezuper@zup.com.br", "417.701.438-90",
				Instant.parse("1995-08-11T00:00:00Z"));
		User u2 = new User(null, "Usuário Zupper", "usuariozuper@zup.com.br", "208.941.580-07",
				Instant.parse("1999-02-20T00:00:00Z"));

		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
