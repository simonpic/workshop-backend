package io.wiiisdom.ticketservice.ressources;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TicketDto {
    private String id;
    private String label;
    private String description;
    private String status;
    private String authorId;
    private String assigneeId;
}
