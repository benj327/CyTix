package main.java.Testing.Experiments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.Testing.Experiments.User;
import java.util.List;

@Service
public class CRUD {
    @Autowired
        User user;      
     
        public User createUser(User U) {
            return user.save(U);
        }

        public void deleteUser(int ID) {
            user.deleteById(ID);
        }
        
        public User updateUser(int ID, User changeUser) {
                User emp = user.findById(user).get();
                emp.setFirstName(changeUser.getFirstName());
                emp.setLastName(changeUser.getLastName());
                emp.setEmail(changeUser.getEmail());
                
                return user.save(user);                                
        }
}
