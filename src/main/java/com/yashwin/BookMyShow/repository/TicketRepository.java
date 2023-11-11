package com.yashwin.BookMyShow.repository;

import com.yashwin.BookMyShow.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Override
    Optional<Ticket> findById(Long ticketId);
    Ticket save(Ticket ticket);
}