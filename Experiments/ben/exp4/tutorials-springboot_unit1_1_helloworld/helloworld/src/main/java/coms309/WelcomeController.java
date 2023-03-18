package coms309;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Hello World, this is exp1";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "Hello and welcome to COMS 309: " + name;
    }
    
    @GetMapping("/time")
    public String time() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        String output = formatter.format(date);
        return output;
    }
    
    @PostMapping("/save/{name}")
    public String postEx(@PathVariable String name) {
    	//in this method I would call the repository to 
    	return "THis is a post example for : " + name;
    }
    
    @PutMapping("/putdemo")
    public String putEx() {
    	//adding a value to the repository for the object attribute
    	return "this is an example of put";
    }
    
    @DeleteMapping("/deletedemo")
    public String deleteDemo() {
    	//removes the attribute from the object
    	return "delete request sent";
    }
}
