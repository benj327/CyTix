package com.example.exp3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.exp3.model.Exp3;
import com.example.exp3.service.Exp3Service;

@RestController
@RequestMapping("/api")
public class Exp3Controller {
        @Autowired
        Exp3Service empService;
        
        @RequestMapping(value="/exp3s", method=RequestMethod.POST)
        public Exp3 createExp3(@RequestBody Exp3 emp) {
            return empService.createExp3(emp);
        }
        
        @RequestMapping(value="/exp3s", method=RequestMethod.GET)
        public List<Exp3> readExp3() {
            return empService.getExp3();
        }

        @RequestMapping(value="/exp3s/{empId}", method=RequestMethod.PUT)
        public Exp3 readEsp3(@PathVariable(value = "empId") Long id, @RequestBody Exp3 empDetails) {
            return empService.updateExp3(id, empDetails);
        }

        @RequestMapping(value="/exp3s/{empId}", method=RequestMethod.DELETE)
        public void deleteExp3(@PathVariable(value = "empId") Long id) {
            empService.deleteExp3(id);
        }

}