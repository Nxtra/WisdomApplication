package com.sample.scrumboard.Controllers;

import com.sample.scrumboard.Models.User;
import com.sample.scrumboard.Models.UserStory;
import com.sample.scrumboard.Repositories.UserRepository;
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
    public ResponseEntity<User> getUserById(Long id){
        if(repository.findOne(id) != null){
            return ok(repository.findOne(id));
        }
        return badRequest().build();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<String> count(){
        return ok(String.format("<b>De database bevat %d users</b>",repository.count()));
    }

    @PutMapping(value = "/add")
    public ResponseEntity<User> putStatementThroughApi(@RequestBody @Valid User user){
        if(repository.findByEmailEquals(user.getEmail())!= null){
            return badRequest().build();
        }
        return ok(repository.save(user));
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<User> deleteOne(@PathVariable Long id, @RequestBody UserStory user){
        if(repository.getOne(id) != null){
            repository.delete(id);
            return ok().build();
        }else{
            return badRequest().build();
        }
    }
}
