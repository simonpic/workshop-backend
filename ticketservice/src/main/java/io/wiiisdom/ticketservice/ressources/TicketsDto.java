package io.wiiisdom.ticketservice.ressources;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TicketsDto {
    private List<TicketDto> tickets;
}
