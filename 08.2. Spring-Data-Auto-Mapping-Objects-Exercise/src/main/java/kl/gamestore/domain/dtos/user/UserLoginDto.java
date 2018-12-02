package kl.gamestore.domain.dtos.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDto {
    private String email;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
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
}
