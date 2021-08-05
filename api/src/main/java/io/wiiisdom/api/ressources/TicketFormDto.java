package io.wiiisdom.api.ressources;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class TicketFormDto {

    @NotBlank
    @Size(min = 5, max = 40)
    private String label;

    private String description;

    @NotBlank
    @Pattern(regexp = "New|InProgress|Done")
    private String status;

    //TODO will be fetched from jwt
    @NotBlank
    private String authorId;

    private String assigneeId;

}
