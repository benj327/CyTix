package com.example.demo.service;

import com.example.demo.entity.Exp5;

import java.util.List;
  
public interface Exp5Service {
    // save operation
    Exp5 saveExp5(Exp5 exp5);
  
    // read operation
    List<Exp5> fetchExp5List();
  
    // update operation
    Exp5 updateExp5(Exp5 exp5, Long exp5Id);
  
    // delete operation
    void deleteExp5ById(Long exp5Id);
}