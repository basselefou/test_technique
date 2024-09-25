package com.fanja.ticketmanagementtest.mapper;

import com.fanja.ticketmanagementtest.dtos.UserDto;
import com.fanja.ticketmanagementtest.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Convert a UserDto object to a User one
     */
    User toEntity(UserDto userDto);

    /**
     * Convert a User object to a UserDto one
     */
    UserDto fromEntity(User user);
}
