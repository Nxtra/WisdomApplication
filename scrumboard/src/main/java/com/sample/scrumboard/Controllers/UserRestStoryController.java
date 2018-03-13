package com.sample.scrumboard.Controllers;

import com.sample.scrumboard.Models.UserStory;
import com.sample.scrumboard.Repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path="/story")
public class UserRestStoryController {

    private UserStoryRepository repository;

    @Autowired
    public UserRestStoryController(UserStoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UserStory> getStatements(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserStory> getStatement(@PathVariable long id){
        if(repository.findOne(id) != null){
            return ok(repository.findOne(id));
        }
        return badRequest().build();
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

    @PutMapping(value = "/put/{story}")
    public ResponseEntity<UserStory> putStatementThroughURL(@PathVariable String story, @RequestBody UserStory userStory) {
        userStory.setStory(story);
        if (repository.findByStoryEquals(story) != null) {
            return badRequest().build();
        }
        return ok(repository.save(userStory));
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<UserStory> deleteOne(@PathVariable Long id, @RequestBody UserStory userStory){
        if(repository.getOne(id) != null){
            repository.delete(id);
            return ok().build();
        }else{
            return badRequest().build();
        }
    }
}
