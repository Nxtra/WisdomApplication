package com.sample.scrumboard.Controllers;

import com.sample.scrumboard.models.UserStory;
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
public class UserStoryController {

    private UserStoryRepository repository;

    @Autowired
    public UserStoryController(UserStoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<UserStory> getStatements(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserStory getStatement(@PathVariable long id){
        return repository.findOne(id);
    }

    @PutMapping
    public ResponseEntity<UserStory> putStatementThroughApi(@RequestBody @Valid UserStory userStory){
        if(repository.findByStoryEquals(userStory.getStory())!= null){
            return badRequest().build();
        }
        return ok(repository.save(userStory));
    }

    @PutMapping(value = "/put/{sentence}")
    public ResponseEntity<UserStory> putStatementThroughURL(@PathVariable String sentence, @RequestBody UserStory userStory) {
        userStory.setStory(sentence);
        if (repository.findByStoryEquals(sentence) != null) {
            return badRequest().build();
        }
        return ok(repository.save(userStory));
    }


    @GetMapping(value = "/count")
    public ResponseEntity<String> count(){
        return ok(String.format("<b>De statement database bevat %d statements</b>",repository.count()));
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
