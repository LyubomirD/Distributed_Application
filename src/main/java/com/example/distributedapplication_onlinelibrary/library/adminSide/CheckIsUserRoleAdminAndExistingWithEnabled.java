package com.example.distributedapplication_onlinelibrary.library.adminSide;

import com.example.distributedapplication_onlinelibrary.exceptions.user_role_access_exception.AdminAccessDeniedException;
import com.example.distributedapplication_onlinelibrary.exceptions.token_exceptions.EmailTokenNotEnablesException;
import com.example.distributedapplication_onlinelibrary.models.users.UserModel;
import com.example.distributedapplication_onlinelibrary.models.users.UserRole;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

        UserModel currentUser = (UserModel) authentication.getPrincipal();
        if (!currentUser.isEnabled()) {
            throw new EmailTokenNotEnablesException("User token is not conformed");
        }

        if (!isAdmin || !authentication.isAuthenticated()) {
            throw new AdminAccessDeniedException("Access is denied");
        }

        return true;
    }
}
