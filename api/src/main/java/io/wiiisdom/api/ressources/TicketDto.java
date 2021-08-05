package io.wiiisdom.api.ressources;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private String id;
    private String label;
    private String description;
    private String status;
    private UserDto author;
    private UserDto assignee;
}
