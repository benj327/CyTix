package com.cytix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cytix.model.Event;
import com.cytix.model.User;
import com.cytix.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

        @Autowired
        UserRepository userRepository;    
        
        public User createUser(User user) {
        	return userRepository.save(user);
        }
        
        public List<User> getUsers(){
        	return userRepository.findAll();
        }
        
        public void deleteUser(Long user_id) {
        	userRepository.deleteById(user_id);
        }
        
        public User updateUser(Long user_id, User userDetails) {
        	User user = userRepository.findById(user_id).get();
        	user.setUserName(userDetails.getUserName());
        	user.setUserEmail(userDetails.getUserEmail());
        	user.setAccountType(userDetails.getAccountType());
        	user.setPassword(userDetails.getPassword());
        	user.setEvent(userDetails.getEvent()); //needed?
        	
        	return userRepository.save(user);
        }
        
        public User findUser(Long user_id) {
        	return userRepository.getById(user_id);
        }
        
        public boolean validateUser(User user) {
        	List<User> allUsers = userRepository.findAll();
        	//efficiency could be improved with a different type of search
        	for(int i = 0; i < allUsers.size(); i++) {
        		if(user.getUserEmail().equals(allUsers.get(i).getUserEmail())) {
        			if(user.getPassword().equals(allUsers.get(i).getPassword())) {
        				return true;
        			}
        		}
        	}
        	return false;
        }
}
