package kl.gamestore.domain.dtos.user;

import kl.gamestore.domain.entities.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegisterDto extends  BaseEntity {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;


    public UserRegisterDto() {
    }

    public UserRegisterDto( String email, String password, String confirmPassword, String fullName) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @NotNull(message = "Full name is required!")
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotNull(message = "Email name is required!")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$", message = "Invalid email!")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Password name is required!")
    @Size(min = 6)
    @Pattern(regexp = "((?=.*[a-z]).*(?=.*[A-Z]).*(?=.*\\d).*).{6,}", message = "Invalid password!")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull(message = "Confirm Password name is required!")
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
