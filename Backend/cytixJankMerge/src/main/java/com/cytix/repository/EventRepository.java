package com.cytix.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cytix.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
