package are.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import are.auth.entities.User;
import are.auth.repositories.users.IUserRepository;

@SpringBootTest
class AuthApplicationTests {

	// @Autowired
	// private TestEntityManager entityManager;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
	}

	@Test
	void saveNewUserTest_shouldBeEqualId() {
		User user = new User();
		user.setId(1L);
		user.setEmail("fnx@gmail.com");
		user.setPassword(passwordEncoder.encode("pass"));
		user.setRole("admin");
		// entityManager.persist(user);
		this.userRepository.save(user);

		Optional<User> savedUser = this.userRepository.findById(1L);
		assertEquals(user.getId(), savedUser.get().getId());
	}

	@Test
	void deleteUserTest_shouldBeNull() {
		User user = new User();
		user.setId(1L);
		user.setEmail("fnx@gmail.com");
		user.setPassword(passwordEncoder.encode("pass"));
		user.setRole("admin");
		// entityManager.persist(user);
		this.userRepository.save(user);

		Optional<User> savedUser = this.userRepository.findById(1L);
		this.userRepository.delete(savedUser.get());
		Optional<User> deletedUser = this.userRepository.findById(1L);
		assertNull(deletedUser.get());
	}

}
