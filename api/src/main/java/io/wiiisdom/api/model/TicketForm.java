package io.wiiisdom.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketForm {
    private String label;
    private String description;
    private String status;
    private String authorId;
    private String assigneeId;
}
