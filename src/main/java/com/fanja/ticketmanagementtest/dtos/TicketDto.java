package com.fanja.ticketmanagementtest.dtos;

import com.fanja.ticketmanagementtest.enums.StatutTicket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class TicketDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String titre;

    private String description;

    @Schema(description = "Statut du ticket", example = "EN_COURS", allowableValues = {"ANNULE", "EN_COURS", "TERMINE"})
    private StatutTicket statutTicket;

    private Long userId;
}
