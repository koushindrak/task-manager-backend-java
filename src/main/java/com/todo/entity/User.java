package com.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.todo.constants.CommonConstants;
import com.todo.constants.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Data
public class User extends ParentEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",length = 50)
    @Size(min = 3,max = 50)
    private String firstName;

    @Column(name = "last_name",length = 50)
    private String lastName = CommonConstants.EMPTY_STRING;

    @Column(nullable = false, unique = true,length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "phone_number",length = 12)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",columnDefinition = "ENUM('ADMIN', 'USER')")
    private Role role;

    /// mapping-   users-groups==> M2M, user-labels==> 12M, user-tokens==> 12M
   // @ManyToMany // will be having users_groups table
//    @JsonIgnoreProperties("users")
    @ManyToMany
    @JoinTable(name = "groups_users", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    @OneToMany(mappedBy = "user") // will be having user_id on many side, i.e in label table
    @JsonIgnoreProperties("user")
    private Set<Label> labels = new HashSet<>();

    @OneToMany(mappedBy = "user") // will be having user_id on many side, i.e in login_details table
    @JsonIgnoreProperties("user")
    private List<LoginDetails> tokens;

    @OneToMany(mappedBy = "user") // will be having user_id on many side, i.e in tasks table
    @JsonIgnoreProperties("user")
    private Set<Task> tasks;

    @OneToMany(mappedBy = "user")
    private Set<Project> projects;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}