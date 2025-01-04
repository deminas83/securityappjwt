package ru.demin.spbootsecurity.spbootsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.repo.PeopleRepo;

import java.util.Optional;

@Service
public class PersonService {
    private  final PeopleRepo peopleRepo;
     private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PeopleRepo peopleRepo, PasswordEncoder passwordEncoder) {
        this.peopleRepo = peopleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean findPersonByName(String name){
        Optional<Person> person = peopleRepo.findByUsername(name);
        return person.isPresent();
    }

    @Transactional
    public void addPerson(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepo.save(person);
    }
}
