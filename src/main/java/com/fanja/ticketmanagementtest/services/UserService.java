package com.fanja.ticketmanagementtest.services;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findAll();

    UserDto create(UserDto userDto);

    UserDto update(UserDto userDto) throws Exception;

    List<TicketDto> getTicketsAssignedToUser(Long userId);

    UserDto getUserById(Long id);

    public boolean isUserAllowedAccess(Long userId, String username);
}
