package com.winterchen.sentry.controller;


import com.winterchen.sentry.service.SentryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/22 11:10 上午
 **/
@RequestMapping("/sentry")
@RestController
@RequiredArgsConstructor
public class SentryController {


    private final SentryService sentryService;


    @GetMapping("/roles/all")
    public ResponseEntity<?> listAllRoles(
            @RequestParam("username")
            String username
    ) {
        return ResponseEntity.ok(sentryService.listAllRoles(username));
    }

    @PostMapping("/role")
    public ResponseEntity<?> createRole(
            @RequestParam("username")
                    String username,
            @RequestParam("role")
                    String role
    ) {
        sentryService.createRole(username, role);
        return ResponseEntity.ok().build();
    }

}
