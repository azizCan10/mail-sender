package com.test.mailSender.dto;

public record AuthRequest(
        String username,
        String password
) {
}
