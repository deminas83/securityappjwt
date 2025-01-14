package ru.demin.spbootsecurity.spbootsecurity.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demin.spbootsecurity.spbootsecurity.dto.AuthenticationDTO;
import ru.demin.spbootsecurity.spbootsecurity.dto.PersonDTO;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.security.JWTUtil;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonDetailsService;
import ru.demin.spbootsecurity.spbootsecurity.services.PersonService;
import ru.demin.spbootsecurity.spbootsecurity.util.PersonValidator;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(PersonService personService, PersonValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.personService = personService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String registrationPage(@ModelAttribute("person") Person person, BindingResult bindingResult){

        return "auth/registration";
    }

    @PostMapping("/register")
    public Map<String, String> addPerson (@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        personValidator.validate(dtoToPerson(personDTO), bindingResult);
        Person person = dtoToPerson(personDTO);
        if (bindingResult.hasErrors()) {
            return Map.of("message","ошибка");}
        personService.addPerson(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e){
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token",token);
    }

    public Person dtoToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonDTO personToDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }
}
