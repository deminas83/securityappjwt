package ru.demin.spbootsecurity.spbootsecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonDetailsService personDetailsService;

    public AuthController(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }

    @PostMapping("/register")
    public String addPerson (@RequestParam("name") @Valid String username, @RequestParam("password") @Valid String password,
                             @RequestParam("dob") @Valid String dob){
        personDetailsService.addPerson(username, password, dob);
        return "redirect:/login";
    }
}
