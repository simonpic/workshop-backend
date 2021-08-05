package io.wiiisdom.ticketservice.repositories;

import io.wiiisdom.ticketservice.entities.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String> {
    @Query("SELECT t FROM Ticket t WHERE t.authorId = ?1 OR t.assigneeId = ?1")
    List<Ticket> findUserTickets(String id);
}
