package com.example.demo.service;

import com.example.demo.entity.Exp5; 
import com.example.demo.repository.Exp5Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
  
import java.util.List;
import java.util.Objects;
  
@Service
public class Exp5ServiceImpl implements Exp5Service{
  
    @Autowired
    private Exp5Repository exp5Repository;
  
    // save operation
    @Override
    public Exp5 saveExp5(Exp5 exp5) {
        return exp5Repository.save(exp5);
    }
  
    // read operation
    @Override
    public List<Exp5> fetchExp5List() {
        return (List<Exp5>) exp5Repository.findAll();
    }
  
    // update operation
    @Override
    public Exp5 updateExp5(Exp5 exp5, Long exp5Id) {
        Exp5 depDB = exp5Repository.findById(exp5Id).get();
  
        if (Objects.nonNull(exp5.getName()) && !"".equalsIgnoreCase(exp5.getName())) {
            depDB.setName(exp5.getName());
        }
  
        if (Objects.nonNull(exp5.getAddress()) && !"".equalsIgnoreCase(exp5.getAddress())) {
            depDB.setAddress(exp5.getAddress());
        }
  
        if (Objects.nonNull(exp5.getCode()) && !"".equalsIgnoreCase(exp5.getCode())) {
            depDB.setCode(exp5.getCode());
        }
  
        return exp5Repository.save(depDB);
    }
  
    // delete operation
    @Override
    public void deleteExp5ById(Long exp5Id) {
        exp5Repository.deleteById(exp5Id);
    }
  
}