package com.example.test_spring;

import com.example.test_spring.entity.User;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;

	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
				TestData.email,
				TestData.password,
				TestData.name
		);
		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());
		//check equals
		Assertions.assertEquals(TestData.email,user.getEmail());

		boolean match = userService.matchPassword(TestData.password,user.getPassword());
		Assertions.assertTrue(match);
		Assertions.assertEquals(TestData.name,user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updatedUser = userService.updateName(user.getId(),TestDataUpdate.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(updatedUser.getName(),TestDataUpdate.name);
	}

	@Order(3)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		userService.deleteById(user.getId());
		Optional<User> optDelete = userService.findByEmail(TestData.email);
		Assertions.assertTrue(optDelete.isEmpty());

	}

	interface TestData{
		String email = "big1@test.com";
		String password = "miracle";
		String name = "big";
	}

	interface TestDataUpdate{
		String name = "biggest";
	}
}
