package com.test.mailSender.dto;

import com.test.mailSender.enums.Role;

import java.util.Set;

public record CreateUserRequest(
        String name,
        String username,
        String email,
        String password,
        Set<Role> authorities
) {
}
