package ru.demin.spbootsecurity.spbootsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demin.spbootsecurity.spbootsecurity.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepo extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);
}
