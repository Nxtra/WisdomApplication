package com.sample.scrumboard.controllers;

import com.sample.scrumboard.dtos.UserDTO;
import com.sample.scrumboard.models.User;
import com.sample.scrumboard.models.UserStory;
import com.sample.scrumboard.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path="/user")
public class UserRestController {

    private UserRepository repository;

    @Autowired
    public UserRestController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping(value = "/list")
    public List<User> getUsers(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id, User user, ModelMapper modelMapper){
            user = repository.getOne(id);
            return modelMapper.map(user, UserDTO.class);
    }

    @GetMapping(value = "/count")
    public ResponseEntity<String> count(){
        return ok(String.format("<b>De database bevat %d users</b>",repository.count()));
    }

    @PutMapping(value = "/add")//does not work
    public ResponseEntity<User> putStatementThroughApi(@RequestBody @Valid User user){
        if(repository.findByEmailEquals(user.getEmail())!= null){
            return badRequest().build();
        }
        return ok(repository.save(user));
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<User> deleteOne(@PathVariable Long id, @RequestBody UserStory user) {
        repository.deleteById(id);
        return ok().build();
    }
}
