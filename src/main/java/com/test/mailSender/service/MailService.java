package com.test.mailSender.service;

import com.test.mailSender.dto.MailRequest;
import com.test.mailSender.dto.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${my.github.link}")
    private String githubLink;

    @Async
    public void sendSimpleMailMessage(UserDTO user, MailRequest mailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setSubject(mailRequest.subject());
            message.setTo(user.getEmail());
            message.setText(mailRequest.text());

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Async
    public void sendAttachmentMail(UserDTO user, MailRequest mailRequest) {
        try {
            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setSubject(mailRequest.subject());
            helper.setTo(user.getEmail());
            helper.setText(mailRequest.text());

            addAttachment(helper, mailRequest.files());

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Async
    public void sendHtmlMail(UserDTO user, MailRequest mailRequest) {
        try {
            Context context = new Context();
            context.setVariable("subject", mailRequest.subject());
            context.setVariable("name", user.getName());
            context.setVariable("url", githubLink);

            String text = templateEngine.process("email-template", context);
            MimeMessage message = getMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(from);
            helper.setSubject(mailRequest.subject());
            helper.setTo(user.getEmail());
            helper.setText(text, true);

            mailSender.send(message);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return mailSender.createMimeMessage();
    }

    private void addAttachment(MimeMessageHelper helper, List<String> files) {
        files.forEach(path -> {
            FileSystemResource file = new FileSystemResource(new File(path));
            try {
                helper.addAttachment(file.getFilename(), file);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
