package com.fanja.ticketmanagementtest.repository;

import com.fanja.ticketmanagementtest.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {


    List<Ticket> findAllByUserId(Long id);
}
