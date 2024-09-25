package com.fanja.ticketmanagementtest.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    private String username;

    private String email;

    private List<TicketDto> ticketDtoList = new ArrayList<>();
}
