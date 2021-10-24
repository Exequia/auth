package are.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import are.auth.entities.People;
import are.auth.entities.Role;
import are.auth.entities.User;
import are.auth.repositories.roles.IRoleRepository;
import are.auth.repositories.users.IUserRepository;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private Long roleId = 1L;

	@Test
	void contextLoads() {
	}

	@Test
	void saveNewRole_shouldBeEqualId() {
		Role role = new Role();
		role.setId(roleId);
		role.setName("god");

		Role newRole = this.roleRepository.save(role);
		roleId = newRole.getId();
		assertEquals(role.getId(), newRole.getId());
	}

	@Test
	void saveNewUserTest_shouldBeEqualId() {
		User user = new User();
		user.setId(1L);
		user.setEmail("fnx@gmail.com");
		user.setPassword(passwordEncoder.encode("pass"));
		user.setAlias("Fénix");

		Role role = new Role();
		role.setId(roleId);
		user.setRole(role);

		People people = new People();
		people.setId(1L);
		people.setFirstName("Alberto");
		people.setLastName("Real");
		people.setsurname("Estepa");
		user.setPeople(people);

		User userData = this.userRepository.save(user);

		Optional<User> savedUser = this.userRepository.findByEmail(user.getEmail());
		assertEquals(userData.getId(), savedUser.get().getId());
	}

	@Test
	void deleteUserTest_shouldBeNull() {
		// User user = new User();
		// user.setId(1L);
		// user.setEmail("fnx@gmail.com");
		// user.setPassword(passwordEncoder.encode("pass"));

		// Role role = new Role();
		// role.setId(1L);
		// user.setRole(role);
		// this.userRepository.save(user);

		Optional<User> savedUser = this.userRepository.findById(1L);
		this.userRepository.delete(savedUser.get());
		Optional<User> deletedUser = this.userRepository.findById(1L);
		assertNull(deletedUser.get());
	}

}
