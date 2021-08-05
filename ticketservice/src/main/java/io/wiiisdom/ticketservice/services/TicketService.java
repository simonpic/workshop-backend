package io.wiiisdom.ticketservice.services;

import io.wiiisdom.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getUserTickets(String id);
    Ticket createTicket(Ticket ticket);
}
