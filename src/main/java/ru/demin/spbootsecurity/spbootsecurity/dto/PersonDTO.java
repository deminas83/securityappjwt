package ru.demin.spbootsecurity.spbootsecurity.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "имя не может быть пустым!")
    @Size(min = 2, max=100 ,message = "Имя должно быть 2-100 символов")
    private String username;

    @NotNull
    @Min( value = 1900, message = "Год должен быть больше 1900")
    private int year_of_birth;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
