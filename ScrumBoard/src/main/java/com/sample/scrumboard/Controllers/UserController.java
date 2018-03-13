package com.sample.scrumboard.Controllers;

import com.sample.scrumboard.Models.User;
import com.sample.scrumboard.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository repository;

    @Autowired
    public UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/user")
    public String userForm(Model model, User user) {
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/user")
    public String userSubmit(@Valid @ModelAttribute User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user";
        }

        repository.save(user);
        return "userSuccess";
    }

}