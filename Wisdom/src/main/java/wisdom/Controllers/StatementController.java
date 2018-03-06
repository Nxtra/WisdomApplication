package wisdom.Controllers;

import wisdom.Repositories.StatementRepository;
import wisdom.models.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class StatementController {

    private StatementRepository repository;

    @Autowired
    public StatementController(StatementRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/statements")
    public List<Statement> getStatements(){
        return repository.findAll();
    }

    @GetMapping(value = "/statements/{id}")
    public Statement getStatement(@PathVariable long id){
        return repository.findOne(id);
    }

    @PutMapping(value = "/statements")
    public ResponseEntity<Statement> putStatementThroughApi(@RequestBody @Valid Statement statement){
        if(repository.findBySentenceEquals(statement.getSentence())!= null){
            return badRequest().build();
        }
        return ok(repository.save(statement));
    }

    @PutMapping(value = "/statements/put/{sentence}")
    public ResponseEntity<Statement> putStatementThroughURL(@PathVariable String sentence, @RequestBody Statement statement) {
        statement.setSentence(sentence);
        if (repository.findBySentenceEquals(sentence) != null) {
            return badRequest().build();
        }
        return ok(repository.save(statement));
    }


    @GetMapping(value = "/statements/count")
    public ResponseEntity<String> count(){
        return ok(String.format("<b>De statement database bevat %d statements</b>",repository.count()));
    }

    @DeleteMapping(value="/statements/delete/{id}")
    public ResponseEntity<Statement> deleteOne(@PathVariable Long id, @RequestBody Statement statement){
        if(repository.getOne(id) != null){
            repository.delete(id);
            return ok().build();
        }else{
            return badRequest().build();
        }
    }
}
