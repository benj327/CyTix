package com.cytix.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cytix.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}

