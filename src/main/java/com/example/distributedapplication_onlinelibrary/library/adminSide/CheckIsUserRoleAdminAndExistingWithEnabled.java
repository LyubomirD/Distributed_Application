package com.example.distributedapplication_onlinelibrary.library.adminSide;

import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserRole;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Collection;

@Service
public class CheckIsUserRoleAdminAndExistingWithEnabled {

    private static final UserRole ADMIN = UserRole.ADMIN;

    @SneakyThrows
    public boolean isUserRoleAdminElseThrowInvalidAccessRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean isAdmin = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals(ADMIN.toString()));

        if (!isAdmin || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Access is denied");
        }

        UserModel currentUser = (UserModel) authentication.getPrincipal();
        if (!currentUser.isEnabled()) {
            throw new AccessDeniedException("User is not enabled");
        }

        return true;
    }
}
