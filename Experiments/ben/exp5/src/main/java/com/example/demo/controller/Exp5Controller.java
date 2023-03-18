package com.example.demo.controller;

import com.example.demo.entity.Exp5;
import com.example.demo.service.Exp5Service;
import java.util.List;
//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
  
// Annotation
@RestController
  
// Class
public class Exp5Controller {
  
    // Annotation
    @Autowired private Exp5Service exp5Service;
  
    // Save operation
    @PostMapping("/exp5")
    public Exp5 saveExp5( //@Valid
        @RequestBody Exp5 exp5)
    {
  
        return exp5Service.saveExp5(exp5);
    }
  
    // Read operation
    @GetMapping("/exp5")
    public List<Exp5> fetchExp5List()
    {
  
        return exp5Service.fetchExp5List();
    }
  
    // Update operation
    @PutMapping("/exp5/{id}")
    public Exp5
    updateExp5(@RequestBody Exp5 exp5,
                     @PathVariable("id") Long exp5Id)
    {
  
        return exp5Service.updateExp5(
            exp5, exp5Id);
    }
  
    // Delete operation
    @DeleteMapping("/exp5/{id}")
    public String deleteExp5ById(@PathVariable("id")
                                       Long exp5Id)
    {
  
        exp5Service.deleteExp5ById(
            exp5Id);
        return "Deleted Successfully";
    }
}