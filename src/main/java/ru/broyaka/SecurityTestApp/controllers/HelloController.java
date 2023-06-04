package ru.broyaka.SecurityTestApp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.broyaka.SecurityTestApp.models.Person;
import ru.broyaka.SecurityTestApp.security.PersonDetails;

@Controller
public class HelloController {

    @GetMapping("/")
    public String sayHello(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
            PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
            model.addAttribute("person", personDetails.getPerson());
        } else {
            model.addAttribute("person", new Person());
        }
        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
