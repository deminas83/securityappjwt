package ru.demin.spbootsecurity.spbootsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.repo.PeopleRepo;

import java.util.Optional;

@Service
public class PersonService {
    private  final PeopleRepo peopleRepo;

    @Autowired
    public PersonService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
    }

    public boolean findPersonByName(String name){
        Optional<Person> person = peopleRepo.findByUsername(name);
        if (person.isPresent()) return true;
        return false;
    }
}
