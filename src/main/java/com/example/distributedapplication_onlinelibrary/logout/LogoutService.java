package com.example.distributedapplication_onlinelibrary.logout;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogoutService {
    public String logoutUser() {
        SecurityContextHolder.clearContext();

        return "Logout successful";
    }
}
