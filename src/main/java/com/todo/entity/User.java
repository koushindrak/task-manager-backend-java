package com.todo.entity;

import com.todo.constants.CommonConstants;
import com.todo.constants.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Setter
@Getter
public class User extends ParentEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String lastName = CommonConstants.EMPTY_STRING;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

//    //relationships
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @JoinColumn(name = "role_id")
//    private Role role;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    private LoginDetails loginDetails;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<UserGroupRole> groupRoles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Label> labels = new HashSet<>();


    // -------
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}