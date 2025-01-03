package ru.demin.spbootsecurity.spbootsecurity.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.demin.spbootsecurity.spbootsecurity.security.PersonDetails;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String sayHello(){
        return "hello";
    }

    @GetMapping("/show")
    public String show(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return "hello";
    }
}
