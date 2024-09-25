package com.fanja.ticketmanagementtest.controller.api;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import com.fanja.ticketmanagementtest.dtos.UserDto;
import com.fanja.ticketmanagementtest.utils.constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    @Operation(summary = "Create UserDto", description = "Create a new UserDto", tags = "UserDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created UserDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = constants.APP_API_ROOT + "/users")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws Exception;

    @Operation(summary = "Get all UserDtos", description = "Get a list of UserDtos", tags = "UserDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a list of UserDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "UserDto Not Found", content = @Content())
    })
    @GetMapping(value = constants.APP_API_ROOT + "/users")
    ResponseEntity<List<UserDto>> findAllUsers();


    @Operation(summary = "Get all TicketDtos assigned to UserDto", description = "GGet all TicketDtos assigned to UserDto", tags = "UserDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get all TicketDtos assigned to UserDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "UserDto Not Found", content = @Content())
    })
    @GetMapping(value = constants.APP_API_ROOT + "/users/ticket/{userId}")
    ResponseEntity<List<TicketDto>> findAllTicketsAssigned(@PathVariable Long userId);

    @Operation(summary = "Update UserDto", description = "Update a UserDto with new values", tags = "UserDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a UserDto with new values", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = constants.APP_API_ROOT + "/users")
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws Exception;


}
