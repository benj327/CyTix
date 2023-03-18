package com.cytix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cytix.model.User;
import com.cytix.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
        @Autowired
        UserService userService;
        
        
        @RequestMapping(value="/users", method=RequestMethod.POST)
        public User createUser(@RequestBody User user) {
        	return userService.createUser(user);
        }
        
        @RequestMapping(value="/users", method=RequestMethod.GET)
        public List<User> readUsers(){
        	return userService.getUsers();
        }
        
        @RequestMapping(value="/users/{user_id}", method=RequestMethod.PUT)
        public User readUsers(@PathVariable(value = "user_id") Long id, @RequestBody User userDetails) {
        	return userService.updateUser(id, userDetails);
        }
        
        @RequestMapping(value="/users/{user_id}", method=RequestMethod.DELETE)
        public void deleteUser(@PathVariable(value = "user_id") Long id) {
        	userService.deleteUser(id);
        }
        
        @RequestMapping(value="/users/validate", method=RequestMethod.GET)
        public boolean validateUser(@RequestBody User user) {
        	return userService.validateUser(user);
        }

}
