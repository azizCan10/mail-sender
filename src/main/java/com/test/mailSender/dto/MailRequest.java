package com.test.mailSender.dto;

import java.util.List;

public record MailRequest(
        String subject,
        String text,
        List<String> files
) {
}
