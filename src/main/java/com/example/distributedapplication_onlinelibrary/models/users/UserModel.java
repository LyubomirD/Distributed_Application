package com.example.distributedapplication_onlinelibrary.models.users;

import com.example.distributedapplication_onlinelibrary.models.books.E_Books;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserModel implements UserDetails {

    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private Integer age;
    private Character sex;
    private Boolean locked = false;
    private Boolean enabled = false;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public UserModel(String fName, String lName, Integer age, Character sex, Boolean locked, Boolean enabled, UserRole userRole) {
        this.fName = fName;
        this.lName = lName;
        this.age = age;
        this.sex = sex;
        this.locked = locked;
        this.enabled = enabled;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @ManyToMany
    @JoinTable(
            name = "users_books",
            joinColumns = @JoinColumn(name = "user_model_id"),
            inverseJoinColumns = @JoinColumn(name = "e_book_id")
    )
    private List<E_Books> eBooks = new ArrayList<>();
}
