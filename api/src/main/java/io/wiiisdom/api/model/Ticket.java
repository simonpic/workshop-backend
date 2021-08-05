package io.wiiisdom.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Ticket {
    private String id;
    private String label;
    private String description;
    private Status status;
    private User author;
    private User assignee;
}
