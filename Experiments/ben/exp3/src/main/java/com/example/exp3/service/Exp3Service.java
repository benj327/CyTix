package com.example.exp3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exp3.model.Exp3;
import com.example.exp3.repository.Exp3Repository;
import java.util.List;

@Service
public class Exp3Service {

        @Autowired
        Exp3Repository empRepository;      
     // CREATE 
        public Exp3 createExp3(Exp3 emp) {
            return empRepository.save(emp);
        }

        // READ
        public List<Exp3> getExp3() {
            return empRepository.findAll();
        }

        // DELETE
        public void deleteExp3(Long empId) {
            empRepository.deleteById(empId);
        }
        
     // UPDATE
        public Exp3 updateExp3(Long empId, Exp3 employeeDetails) {
                Exp3 emp = empRepository.findById(empId).get();
                emp.setFirstName(employeeDetails.getFirstName());
                emp.setLastName(employeeDetails.getLastName());
                emp.setEmailId(employeeDetails.getEmailId());
                
                return empRepository.save(emp);                                
        }
}