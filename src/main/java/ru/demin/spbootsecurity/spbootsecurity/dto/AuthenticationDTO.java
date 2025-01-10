package ru.demin.spbootsecurity.spbootsecurity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.ShiftRight;

public class AuthenticationDTO {
    @NotEmpty(message = "имя не может быть пустым!")
    @Size(min = 2, max=100 ,message = "Имя должно быть 2-100 символов")
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
