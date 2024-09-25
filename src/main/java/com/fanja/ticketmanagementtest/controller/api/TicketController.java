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

public interface TicketController {

    @Operation(summary = "Create TicketDto", description = "Create a new TicketDto", tags = "TicketDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created TicketDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = constants.APP_API_ROOT + "/tickets")
    ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) throws Exception;

    @Operation(summary = "Get all TicketDtos", description = "Get a list of TicketDtos", tags = "TicketDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a list of TicketDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "TicketDto Not Found", content = @Content())
    })
    @GetMapping(value = constants.APP_API_ROOT + "/tickets")
    ResponseEntity<List<TicketDto>> findAllTickets();


    @Operation(summary = "Get a TicketDto", description = "Get a TicketDto with a given id", tags = "TicketDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a TicketDto with a given id", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "TicketDto Not Found", content = @Content())
    })
    @GetMapping(value = constants.APP_API_ROOT + "/tickets/{id}")
    ResponseEntity<TicketDto> findTicketById(@PathVariable Long id);

    @Operation(summary = "Update TicketDto", description = "Update a TicketDto with new values", tags = "TicketDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update a TicketDto with new values", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = constants.APP_API_ROOT + "/tickets")
    ResponseEntity<TicketDto> updateTicket(@RequestBody TicketDto ticketDto) throws Exception;

    @Operation(summary = "Delete a TicketDto", description = "Delete a TicketDto with a given id", tags = "TicketDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete a TicketDto with a given id", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TicketDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "TicketDto Not Found", content = @Content())
    })
    @DeleteMapping(value = constants.APP_API_ROOT + "/tickets/{id}")
    void deleteTicket(@PathVariable Long id);

    @Operation(summary = "Assign a ticket to an UserDto", description = "Assign a ticket to an UserDto", tags = "UserDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assign a ticket to an UserDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping(value = constants.APP_API_ROOT + "/assign/{userId}")
    ResponseEntity<TicketDto> assignTicketToUser(@RequestBody TicketDto ticketDto, @PathVariable Long userId) throws Exception;
}
