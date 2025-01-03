package ru.demin.spbootsecurity.spbootsecurity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "personsecurity")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "имя не может быть пустым!")
    @Size(min = 2, max=100 ,message = "Имя должно быть 2-100 символов")
    private String username;

    @Column(name = "year_of_birth")
    @NotNull
    @Min( value = 1900, message = "Год должен быть больше 1900")
    private int year_of_birth;

    @Column(name = "password")
    @NotNull
    private String password;

    public Person() {
    }

    public @NotEmpty(message = "имя не может быть пустым!") @Size(min = 2, max = 100, message = "Имя должно быть 2-100 символов") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "имя не может быть пустым!") @Size(min = 2, max = 100, message = "Имя должно быть 2-100 символов") String username) {
        this.username = username;
    }

    @NotNull
    @Min(value = 1900, message = "Год должен быть больше 1900")
    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(@NotNull @Min(value = 1900, message = "Год должен быть больше 1900") int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", year_of_birth=" + year_of_birth +
                ", password='" + password + '\'' +
                '}';
    }
}
