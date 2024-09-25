package com.fanja.ticketmanagementtest.services.impl;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.dtos.UserDto;
import com.fanja.ticketmanagementtest.exceptions.EntityNotFoundException;
import com.fanja.ticketmanagementtest.exceptions.ErrorCodes;
import com.fanja.ticketmanagementtest.exceptions.InvalidEntityException;
import com.fanja.ticketmanagementtest.exceptions.InvalidOperationException;
import com.fanja.ticketmanagementtest.mapper.TicketMapper;
import com.fanja.ticketmanagementtest.mapper.UserMapper;
import com.fanja.ticketmanagementtest.repository.TicketRepository;
import com.fanja.ticketmanagementtest.repository.UserRepository;
import com.fanja.ticketmanagementtest.services.UserService;
import com.fanja.ticketmanagementtest.validators.TicketValidator;
import lombok.extern.slf4j.Slf4j;
import com.fanja.ticketmanagementtest.entities.Ticket;
import com.fanja.ticketmanagementtest.entities.User;
import com.fanja.ticketmanagementtest.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    public List<TicketDto> findAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private TicketDto convertToDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .titre(ticket.getTitre())
                .description(ticket.getDescription())
                .statutTicket(ticket.getStatutTicket())
                .userId(ticket.getUser() != null ? ticket.getUser().getId() : null)
                .build();
    }

    private Ticket convertToEntity(TicketDto ticketDto, User user) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setTitre(ticketDto.getTitre());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setStatutTicket(ticketDto.getStatutTicket());
        ticket.setUser(user);
        return ticket;
    }

    private Ticket convertTicketToEntity(TicketDto ticketDto) {
        if (ticketDto == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketDto.getId());
        ticket.setTitre(ticketDto.getTitre());
        ticket.setDescription(ticketDto.getDescription());
        ticket.setStatutTicket(ticketDto.getStatutTicket());


        return ticket;
    }

    public User convertToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());

        if (userDto.getTicketDtoList() != null) {
            user.setTicketList(userDto.getTicketDtoList().stream()
                    .map(this::convertTicketToEntity)
                    .collect(Collectors.toList()));
        }

        return user;
    }



    @Override
    public void deleteTicket(Long id) {
        if (id != null) {
            Ticket ticket = ticketRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ticket with id " + id + " not found"));
            ticketRepository.delete(ticket);
            log.info("delete() - Facture with id {} deleted successfully", id);
        } else {
            log.error("delete() - Failed to delete Facture. Id is null");
        }
        }

    @Override
    public TicketDto findTicketById(Long id) {
        if (id == null) {
            log.error("Ticket ID is null");
            return null;
        }
        return ticketRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        "no ticket with id" + id + "found",
                        ErrorCodes.TICKET_NOT_FOUND)
                );

    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto) throws Exception {

        if (ticketDto == null) {
            throw new Exception("Failed to update Ticket : provided TicketDto is null !");
        } else {

            Long ticketDtoId = ticketDto.getId();
            Ticket ticket = ticketRepository.findById(ticketDtoId).orElse(null);

            User user = null;
            if (ticketDto.getUserId() != null) {
                user = userRepository.findById(ticketDto.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"));
            }

               Ticket t1 = convertToEntity(ticketDto, user);

                Ticket ticket1 = ticketRepository.save(t1);

                return convertToDto(ticket1);
            }

    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) throws Exception {
        List<String> errors = TicketValidator.validate(ticketDto);
        if (!errors.isEmpty()) {
            log.error("Ticket is not valid {}", ticketDto);
            throw new InvalidEntityException("Ticket is not valid", ErrorCodes.TICKET_NOT_VALID, errors);
        }

        User user = null;
        if (ticketDto.getUserId() != null) {
            user = userRepository.findById(ticketDto.getUserId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
        }

        Ticket t1 = convertToEntity(ticketDto, user);

        Ticket ticket1 = ticketRepository.save(t1);
        return convertToDto(ticket1);
    }

    @Override
    public TicketDto assignTicket(TicketDto ticketDto, Long userId) throws Exception {

            Long ticketDtoId = ticketDto.getId();
            Ticket ticket = ticketRepository.findById(ticketDtoId).orElse(null);

            if (ticket == null) {
                throw new Exception(
                        String.format("Failed to update Ticket : Ticket with id %d not found !", ticketDtoId));
            }

                Long idUser = ticketDto.getUserId();
                if (idUser != null) {
                    throw new InvalidOperationException("Ticket is already assigned !", ErrorCodes.TICKET_ALREADY_ASSIGN);
                }
                else {
                    TicketDto dto = findTicketById(ticketDtoId);
                    ticket.setId(dto.getId());
                    ticket.setDescription(dto.getDescription());
                    ticket.setTitre(dto.getTitre());
                    ticket.setStatutTicket(dto.getStatutTicket());
                    UserDto userDto = userService.getUserById(userId);
                    User user1 = convertToEntity(userDto);

                    Ticket t1 = convertToEntity(dto, user1);

                    Ticket ticket1 = ticketRepository.save(t1);

                    return convertToDto(ticket1);




        }
    }

    @Override
    public List<TicketDto> findAllTicketsByUserId(Long userId) {
        List<Ticket> tickets = new ArrayList<>();

    return List.of();}
}



