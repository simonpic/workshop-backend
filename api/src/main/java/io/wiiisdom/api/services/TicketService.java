package io.wiiisdom.api.services;

import io.wiiisdom.api.model.Ticket;
import io.wiiisdom.api.model.TicketForm;

import java.util.List;

public interface TicketService {
    List<Ticket> getTickets(String id);
    Ticket createTicket(TicketForm ticketForm);
}
