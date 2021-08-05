package io.wiiisdom.ticketservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter@Setter
public class Ticket {
    @Id
    private String id;

    @Column(nullable = false)
    private String label;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String authorId;

    private String assigneeId;
}
