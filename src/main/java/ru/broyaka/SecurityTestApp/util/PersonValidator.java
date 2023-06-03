package ru.broyaka.SecurityTestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.broyaka.SecurityTestApp.models.Person;
import ru.broyaka.SecurityTestApp.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> personFromDB = peopleService.findByUserName(person.getUserName());

        if (personFromDB.isPresent()) {
            errors.rejectValue("userName", "", "Человек с таким никнеймом уже существует");
        }
    }
}
