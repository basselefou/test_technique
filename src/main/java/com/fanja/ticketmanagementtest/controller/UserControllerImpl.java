package com.fanja.ticketmanagementtest.controller;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.fanja.ticketmanagementtest.controller.api.UserController;
import com.fanja.ticketmanagementtest.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> createUser(UserDto userDto) throws Exception {
        log.debug("REST request to create a UserDto object : {} ", userDto);
        UserDto userDto1 = userService.create(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<UserDto>> findAllUsers() {
        log.debug("findUserDto Alls() - REST request to get ALL UserDto ");
        List<UserDto> userDtos = userService.findAll();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TicketDto>> findAllTicketsAssigned(Long userId) {
        log.debug("findAllTicketsAssigned Alls() - REST request to get ALL TicketDto ");
        List<TicketDto> ticketDtos = userService.getTicketsAssignedToUser(userId);
        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<UserDto> updateUser(UserDto userDto) throws Exception {
        log.debug("REST request to update the UserDto object : {} ", userDto);
        UserDto userDto1 = userService.update(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
}
