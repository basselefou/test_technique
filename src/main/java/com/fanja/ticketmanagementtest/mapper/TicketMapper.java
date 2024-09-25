package com.fanja.ticketmanagementtest.mapper;


import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.entities.Ticket;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    /**
     * Convert a TicketDto object to a Ticket one
     */
    Ticket toEntity(TicketDto ticketDto);

    /**
     * Convert a Ticket object to a TicketDto one
     */
    TicketDto fromEntity(Ticket ticket);
}
