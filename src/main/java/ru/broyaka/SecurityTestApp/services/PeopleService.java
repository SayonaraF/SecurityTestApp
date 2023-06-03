package ru.broyaka.SecurityTestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.broyaka.SecurityTestApp.models.Person;
import ru.broyaka.SecurityTestApp.repositories.PeopleRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Person> findByUserName(String userName) {
        return peopleRepository.findByUserName(userName);
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        peopleRepository.save(person);
    }
}
