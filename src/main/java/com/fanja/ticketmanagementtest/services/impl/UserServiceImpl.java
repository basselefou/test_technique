package com.fanja.ticketmanagementtest.services.impl;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.dtos.UserDto;
import com.fanja.ticketmanagementtest.exceptions.EntityNotFoundException;
import com.fanja.ticketmanagementtest.exceptions.ErrorCodes;
import com.fanja.ticketmanagementtest.repository.TicketRepository;
import com.fanja.ticketmanagementtest.repository.UserRepository;
import com.fanja.ticketmanagementtest.services.UserService;
import lombok.extern.slf4j.Slf4j;
import com.fanja.ticketmanagementtest.entities.Ticket;
import com.fanja.ticketmanagementtest.entities.User;
import com.fanja.ticketmanagementtest.mapper.TicketMapper;
import com.fanja.ticketmanagementtest.mapper.UserMapper;
import com.fanja.ticketmanagementtest.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TicketService ticketService;
    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = getUserById(user.getId());
            userDtos.add(userDto);
        }

        return userDtos;
    }

    @Override
    public UserDto create(UserDto userDto) {

        return userMapper.fromEntity(
                userRepository.save(
                        userMapper.toEntity(userDto)
                )
        );
    }

    @Override
    public UserDto update(UserDto userDto) throws Exception{
        if (userDto == null) {
            throw new Exception("Failed to update User : provided UserDto is null !");
        } else {

            Long userDtoId = userDto.getId();
            User user = userRepository.findById(userDtoId).orElse(null);

            if (user == null) {
                throw new Exception(
                        String.format("Failed to update User : User with id %d not found !", userDtoId));
            } else {
                user.setId(userDto.getId());
                user.setEmail(userDto.getEmail());
                user.setUsername(userDto.getUsername());
                }

                User user1 = userRepository.save(user);

                return userMapper.fromEntity(user1);
            }
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


    @Override
    public List<TicketDto> getTicketsAssignedToUser(Long userId) {
        List<Ticket> tickets = ticketRepository.findAllByUserId(userId);
      /*  List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            TicketDto ticketDto = ticketService.findTicketById(ticket.getId());
            ticketDtos.add(ticketDto);
        }*/

        return tickets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());


    }

    @Override
    public UserDto getUserById(Long id) {
        if (id == null) {
            log.error("Ticket ID is null");
            return null;
        }
        return userRepository.findById(id)
                .map(user -> userMapper.fromEntity(user))
                .orElseThrow(() -> new EntityNotFoundException(
                        "no User with id" + id + "found",
                        ErrorCodes.USER_NOT_FOUND)
                );
    }

    @Override
    public boolean isUserAllowedAccess(Long userId, String username) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null && user.getUsername().equals(username);
    }
}
