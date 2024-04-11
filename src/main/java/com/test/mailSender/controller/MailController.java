package com.test.mailSender.controller;

import com.test.mailSender.annotation.LoggedUser;
import com.test.mailSender.dto.MailRequest;
import com.test.mailSender.dto.UserDTO;
import com.test.mailSender.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/send-simple-mail")
    public ResponseEntity<?> sendSimpleMailMessage(@LoggedUser UserDTO user, @RequestBody MailRequest mailRequest) {
        mailService.sendSimpleMailMessage(user, mailRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-attachment-mail")
    public ResponseEntity<?> sendAttachmentMail(@LoggedUser UserDTO user, @RequestBody MailRequest mailRequest) {
        mailService.sendAttachmentMail(user, mailRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send-html-mail")
    public ResponseEntity<?> sendHtmlMail(@LoggedUser UserDTO user, @RequestBody MailRequest mailRequest) {
        mailService.sendHtmlMail(user, mailRequest);
        return ResponseEntity.ok().build();
    }
}
