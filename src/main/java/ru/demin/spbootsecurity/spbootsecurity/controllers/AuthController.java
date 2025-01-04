package ru.demin.spbootsecurity.spbootsecurity.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonService;
import ru.demin.spbootsecurity.spbootsecurity.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;
    private final PersonValidator personValidator;

    @Autowired
    public AuthController(PersonDetailsService personDetailsService, PersonService personService, PersonValidator personValidator) {
        this.personService = personService;
        this.personValidator = personValidator;
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
    public String addPerson (@RequestParam("person") @Valid Person person, BindingResult bindingResult){
        System.out.println("dfsdfsdfsdf");
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {return "auth/registration";}
        personService.addPerson(person);
        return "redirect:auth/registration";
    }
}
