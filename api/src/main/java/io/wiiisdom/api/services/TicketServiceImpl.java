package io.wiiisdom.api.services;

import io.wiiisdom.api.model.Status;
import io.wiiisdom.api.model.Ticket;
import io.wiiisdom.api.model.TicketForm;
import io.wiiisdom.api.model.User;
import io.wiiisdom.api.model.ticket.Tickets;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {

    private final RestTemplate client;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public TicketServiceImpl(@Qualifier("ticketMsClient") RestTemplate client,
                             ModelMapper modelMapper, UserService userService) {
        this.client = client;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public List<Ticket> getTickets(String id) {
        String url = "/tickets?userId=" + id;
        Tickets ticketsAPI = client.getForObject(url, Tickets.class);
        List<Ticket> tickets = new ArrayList<>();

        if (ticketsAPI == null) {
            return tickets;
        }

        Map<String, User> users = new HashMap<>();

        for (io.wiiisdom.api.model.ticket.Ticket from: ticketsAPI.getTickets()) {
            Ticket ticket = mapTicket(from, users);
            tickets.add(ticket);
        }

        return tickets;
    }

    @Override
    public Ticket createTicket(TicketForm ticketForm) {
        io.wiiisdom.api.model.ticket.Ticket ticket = modelMapper.map(ticketForm, io.wiiisdom.api.model.ticket.Ticket.class);
        ResponseEntity<io.wiiisdom.api.model.ticket.Ticket> resp =
                client.postForEntity("/tickets", ticket, io.wiiisdom.api.model.ticket.Ticket.class);
        return modelMapper.map(resp.getBody(), Ticket.class);
    }

    private Ticket mapTicket(io.wiiisdom.api.model.ticket.Ticket from, Map<String, User> users) {
        Ticket ticket = modelMapper.map(from, Ticket.class);

        String authorId = from.getAuthorId();
        if (!users.containsKey(authorId)) {
            User author = userService.getUserById(authorId);
            users.put(authorId, author);
        }
        ticket.setAuthor(users.get(authorId));

        String assigneeId = from.getAssigneeId();
        if (assigneeId != null) {
            if (!users.containsKey(assigneeId)) {
                User assignee = userService.getUserById(assigneeId);
                users.put(assigneeId, assignee);
            }
            ticket.setAssignee(users.get(assigneeId));
        }

        return ticket;
    }
}
