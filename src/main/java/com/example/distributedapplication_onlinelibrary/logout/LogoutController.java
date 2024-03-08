package com.example.distributedapplication_onlinelibrary.logout;

import com.example.distributedapplication_onlinelibrary.login.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/logout")
@AllArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping
    public String logoutUser() {
        return logoutService.logoutUser();
    }
}
