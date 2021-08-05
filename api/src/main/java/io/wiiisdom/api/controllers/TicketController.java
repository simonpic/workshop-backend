package io.wiiisdom.api.controllers;

import io.wiiisdom.api.model.Ticket;
import io.wiiisdom.api.model.TicketForm;
import io.wiiisdom.api.ressources.TicketDto;
import io.wiiisdom.api.ressources.TicketFormDto;
import io.wiiisdom.api.ressources.TicketsDto;
import io.wiiisdom.api.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<TicketDto> tickets = ticketService.getTickets(userId).stream()
                .map(ticket -> modelMapper.map(ticket, TicketDto.class))
                .collect(Collectors.toList());
        return new TicketsDto(tickets);
    }

    @PostMapping
    public TicketDto createTicket(@Valid @RequestBody TicketFormDto ticketFormDto) {
        TicketForm ticketForm = modelMapper.map(ticketFormDto, TicketForm.class);
        Ticket ticket = ticketService.createTicket(ticketForm);
        return modelMapper.map(ticket, TicketDto.class);
    }

}
