package com.neo.security.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.neo.security.entity.User;
import com.neo.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;
	
	@Test
	public void testCreateUser() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "password123";
		String encodedPassword = passwordEncoder.encode(password);
		
		User newUser = User.builder().email("abc@gmail.com").password(encodedPassword).build();
		
		User savedUser = repository.save(newUser);
		
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
}
