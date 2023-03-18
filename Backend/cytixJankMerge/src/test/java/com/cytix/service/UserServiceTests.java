package com.cytix.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.cytix.model.User;
import com.cytix.repository.UserRepository;

public class UserServiceTests{
	
	@Mock
	UserRepository repo;
	
	
	UserService userService = mock(UserService.class);
	
	@Test
	public void createAndGetUsers() {
		User user = new User();
		userService.createUser(user);
		List<User> list = new ArrayList<User>();
		list = userService.getUsers();
		assertNotEquals(null, list);
	}
	
	@Test
	public void deleteUser() {
		User user = new User();
		user.setId((long)1234);
		userService.createUser(user);
		List<User> list = new ArrayList<User>();
		list = userService.getUsers();
		assertNotEquals(null, list);
		userService.deleteUser(user.getId());
		list = userService.getUsers();
		List<User> test = new ArrayList<User>();
		assertEquals(test, list);
	}
	
	@Test
	public void validateUser() {
		User user = new User();
		user.setId((long)1234);
		user.setUserEmail("test");
		user.setPassword("abc");
		userService.createUser(user);
		user.setPassword("ab");
		assertEquals(false, userService.validateUser(user));
	}
	
	
	
	
}