package io.wiiisdom.ticketservice.controllers;

import io.wiiisdom.ticketservice.entities.Ticket;
import io.wiiisdom.ticketservice.ressources.TicketDto;
import io.wiiisdom.ticketservice.ressources.TicketsDto;
import io.wiiisdom.ticketservice.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final ModelMapper modelMapper;

    @Autowired
    public TicketController(TicketService ticketService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public TicketsDto getTickets(@RequestParam String userId) {
        List<TicketDto> tickets = ticketService.getUserTickets(userId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
        return new TicketsDto(tickets);
    }

    @PostMapping
    public TicketDto createTicket(@RequestBody TicketDto ticketDto) {
        Ticket ticket = ticketService.createTicket(modelMapper.map(ticketDto, Ticket.class));
        return modelMapper.map(ticket, TicketDto.class);
    }

}
