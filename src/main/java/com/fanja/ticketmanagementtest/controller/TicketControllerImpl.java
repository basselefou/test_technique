package com.fanja.ticketmanagementtest.controller;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fanja.ticketmanagementtest.controller.api.TicketController;
import com.fanja.ticketmanagementtest.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class TicketControllerImpl implements TicketController {


    private final TicketService ticketService;

    @Override
    public ResponseEntity<TicketDto> createTicket(TicketDto ticketDto) throws Exception {
        log.debug("REST request to create a TicketDTO object : {} ", ticketDto);
        TicketDto ticketDto1 = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(ticketDto1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TicketDto>> findAllTickets() {
        log.debug("findTicketDto Alls() - REST request to get ALL TicketDtos ");
        List<TicketDto> ticketDtos = ticketService.findAllTickets();
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDto> findTicketById(Long id) {
        log.debug("findTicketDtoById() - REST request to get TicketDto with id: {}", id);
        TicketDto ticketDto = ticketService.findTicketById(id);
        return new ResponseEntity<>(ticketDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TicketDto> updateTicket(TicketDto ticketDto) throws Exception {
        log.debug("REST request to update the TicketDto object : {} ", ticketDto);
        TicketDto ticketDto1 = ticketService.updateTicket(ticketDto);
        return new ResponseEntity<>(ticketDto1, HttpStatus.OK);
    }

    @Override
    public void deleteTicket(Long id) {
        log.debug("deleteTicketDto() - REST request to delete TicketDto with id: {}", id);
        ticketService.deleteTicket(id);
    }

    @Override
    public ResponseEntity<TicketDto> assignTicketToUser(TicketDto ticketDto, Long userId) throws Exception {
        log.debug("REST request to update the TicketDto object : {} ", ticketDto);
        TicketDto ticketDto1 = ticketService.assignTicket(ticketDto, userId);
        return new ResponseEntity<>(ticketDto1, HttpStatus.OK);
    }
}
