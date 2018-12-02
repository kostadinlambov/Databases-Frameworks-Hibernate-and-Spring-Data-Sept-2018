package kl.gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User extends  BaseEntity {
    private String fullName;
    private String email;
    private String password;
    private Role role;

    public User() {

    }

    @Column(name = "full_name")
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "email", unique = true)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    @Size(min = 6)
    @Pattern(regexp = "((?=.*[a-z]).*(?=.*[A-Z]).*(?=.*\\d).*).{6,}")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "is_admin")
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
