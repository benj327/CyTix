package com.example.demo.repository;

import com.example.demo.entity.Exp5;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
  
// Annotation
@Repository
  
// Class
public interface Exp5Repository
    extends CrudRepository<Exp5, Long> {
}