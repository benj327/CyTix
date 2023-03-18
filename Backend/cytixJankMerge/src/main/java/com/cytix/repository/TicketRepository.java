package com.cytix.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cytix.model.Tickets;

@Repository
public interface TicketRepository extends JpaRepository<Tickets, Long> {

}