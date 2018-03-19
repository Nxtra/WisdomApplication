package com.sample.scrumboard.controllers;

import com.sample.scrumboard.models.UserStory;
import com.sample.scrumboard.repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path="/story")
public class UserStoryRestController {

    private UserStoryRepository repository;

    @Autowired
    public UserStoryRestController(UserStoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping//until there is a way to add new stories
    public ModelAndView story(){
        return new ModelAndView("redirect:/story/list");
    }

    @GetMapping(value = "/list")
    public List<UserStory> getStatements(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserStory> getStatement(@PathVariable long id){
        try{
            return ok(repository.getOne(id));
        }catch (EntityNotFoundException ex){
            return badRequest().build();
        }
    }

    @GetMapping(value = "/count")
    public ResponseEntity<String> count(){
        return ok(String.format("<b>De database bevat %d user stories </b>",repository.count()));
    }

    //@RequestBody annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
    @PutMapping
    public ResponseEntity<UserStory> putStatementThroughApi(@RequestBody @Valid UserStory userStory){
        if(repository.findByStoryEquals(userStory.getStory())!= null){
            return badRequest().build();
        }
        return ok(repository.save(userStory));
    }

    @DeleteMapping(value="/delete/{id}")
    public void deleteOne(@PathVariable Long id){
            repository.deleteById(id);
    }
}
