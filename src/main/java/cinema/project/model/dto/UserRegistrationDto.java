package cinema.project.model.dto;

import cinema.project.annotation.FieldsValueMatch;
import cinema.project.annotation.LoginConstraint;
import javax.validation.constraints.NotNull;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "verifyPassword",
        message = "Passwords do not match")
public class UserRegistrationDto {
    @NotNull
    private String password;
    @NotNull
    private String verifyPassword;
    @LoginConstraint(message = "Invalid email")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String login) {
        this.email = login;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
