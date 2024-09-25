package com.fanja.ticketmanagementtest.services;

import com.fanja.ticketmanagementtest.dtos.TicketDto;

import java.util.List;

public interface TicketService {

    List<TicketDto> findAllTickets();

    void deleteTicket(Long id);

    TicketDto findTicketById(Long id);

    TicketDto updateTicket(TicketDto ticketDto) throws Exception;

    TicketDto createTicket(TicketDto ticketDto) throws Exception;

    TicketDto assignTicket(TicketDto ticketDto, Long userId) throws Exception;

    List<TicketDto> findAllTicketsByUserId(Long userId);
}
