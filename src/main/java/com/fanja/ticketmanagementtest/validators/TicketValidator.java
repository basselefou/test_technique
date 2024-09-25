package com.fanja.ticketmanagementtest.validators;

import com.fanja.ticketmanagementtest.dtos.TicketDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TicketValidator {

    public static List<String> validate(TicketDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Please enter a valid description ");
            errors.add("please enter a valid title ticket");
            errors.add("Please enter a status ticket");
            errors.add("please input the user assigned ticket");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getDescription())) {
            errors.add("Please enter a valid description ");        }
        if (dto.getTitre() == null) {
            errors.add("please enter a valid title ticket");        }
        if (!StringUtils.hasLength(dto.getStatutTicket().toString())) {
            errors.add("Please enter a status ticket");
        }

        return errors;
    }

}
