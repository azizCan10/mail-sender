package com.test.mailSender.controller;

import com.test.mailSender.dto.CreateUserRequest;
import com.test.mailSender.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }
}
