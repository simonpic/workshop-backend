package io.wiiisdom.ticketservice.services;

import io.wiiisdom.ticketservice.entities.Ticket;
import io.wiiisdom.ticketservice.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<Ticket> getUserTickets(String id) {
        return ticketRepository.findUserTickets(id);
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        ticket.setId(UUID.randomUUID().toString());
        return ticketRepository.save(ticket);
    }

}