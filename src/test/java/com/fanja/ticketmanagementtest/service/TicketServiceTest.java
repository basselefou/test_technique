package com.fanja.ticketmanagementtest.service;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.enums.StatutTicket;
import com.fanja.ticketmanagementtest.entities.Ticket;
import com.fanja.ticketmanagementtest.repository.TicketRepository;
import com.fanja.ticketmanagementtest.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllTickets() {
        Ticket ticket1 = new Ticket(1L, "Ticket1", "Description1", StatutTicket.EN_COURS, null);
        Ticket ticket2 = new Ticket(2L, "Ticket2", "Description2", StatutTicket.TERMINE, null);
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2));

        List<TicketDto> ticketDtos = ticketService.findAllTickets();

        assertEquals(2, ticketDtos.size());
        assertEquals("Ticket1", ticketDtos.get(0).getTitre());
        assertEquals("Ticket2", ticketDtos.get(1).getTitre());
    }

    @Test
    public void testDeleteTicket() {
        Long ticketId = 1L;
        ticketService.deleteTicket(ticketId);

        verify(ticketRepository, times(1)).deleteById(ticketId);
    }

    @Test
    public void testFindTicketById() {
        Ticket ticket = new Ticket(1L, "Ticket1", "Description1", StatutTicket.EN_COURS, null);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        TicketDto ticketDto = ticketService.findTicketById(1L);

        assertNotNull(ticketDto);
        assertEquals("Ticket1", ticketDto.getTitre());
    }

    @Test
    public void testUpdateTicket() throws Exception {
        TicketDto ticketDto = new TicketDto(1L, "UpdatedTicket", "UpdatedDescription", StatutTicket.TERMINE, null);
        Ticket ticket = new Ticket(1L, "OldTicket", "OldDescription", StatutTicket.EN_COURS, null);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto updatedTicketDto = ticketService.updateTicket(ticketDto);

        assertEquals("UpdatedTicket", updatedTicketDto.getTitre());
    }

    @Test
    public void testCreateTicket() throws Exception {
        TicketDto ticketDto = new TicketDto(null, "NewTicket", "NewDescription", StatutTicket.EN_COURS, null);
        Ticket ticket = new Ticket(1L, "NewTicket", "NewDescription", StatutTicket.EN_COURS, null);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto createdTicketDto = ticketService.createTicket(ticketDto);

        assertEquals("NewTicket", createdTicketDto.getTitre());
    }

    @Test
    public void testAssignTicket() throws Exception {
        TicketDto ticketDto = new TicketDto(1L, "Ticket1", "Description1", StatutTicket.EN_COURS, 1L);
        Ticket ticket = new Ticket(1L, "Ticket1", "Description1", StatutTicket.EN_COURS, null);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        TicketDto assignedTicketDto = ticketService.assignTicket(ticketDto, 1L);

        assertEquals(1L, assignedTicketDto.getUserId());
    }
}
