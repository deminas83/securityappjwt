package ru.demin.spbootsecurity.spbootsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;
import ru.demin.spbootsecurity.spbootsecurity.repo.PeopleRepo;
import ru.demin.spbootsecurity.spbootsecurity.security.PersonDetails;
import ru.demin.spbootsecurity.spbootsecurity.util.PersonValidator;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
     private final PeopleRepo peopleRepo;

     @Autowired
    public PersonDetailsService(PeopleRepo peopleRepo) {
        this.peopleRepo = peopleRepo;
     }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person =  peopleRepo.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new PersonDetails(person.get());
    }

}
