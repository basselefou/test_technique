package com.fanja.ticketmanagementtest.validators;

import com.fanja.ticketmanagementtest.dtos.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(UserDto utilisateurDto) {
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasLength(utilisateurDto.getUsername())) {
            errors.add("Please enter a valid username");
        }
        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Please enter a valid email address");
        }
        errors.addAll(UserValidator.validate(utilisateurDto));

        return errors;
    }

}
