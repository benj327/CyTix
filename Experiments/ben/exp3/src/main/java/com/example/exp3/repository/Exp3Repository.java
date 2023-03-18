package com.example.exp3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exp3.model.Exp3;

@Repository
public interface Exp3Repository extends JpaRepository<Exp3, Long> {

}