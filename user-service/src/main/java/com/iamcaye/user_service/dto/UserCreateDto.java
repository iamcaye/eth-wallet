package com.iamcaye.user_service.dto;

import java.util.Date;

import com.iamcaye.user_service.models.UserRole;

public record UserCreateDto(
    String username,
    String full_name,
    String email,
    Date dob,
    UserRole role_id,
    String password
) {
}
