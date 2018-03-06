package wisdom;

import wisdom.Repositories.StatementRepository;
import wisdom.models.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private StatementRepository repository;

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    public DataLoader(StatementRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        repository.save(new Statement("Voor niks gaat de zon op"));
        repository.save(new Statement("Time waits for no man. Unless that man is Chuck Norris."));

        LOG.info("Questions found with findAll():");
        LOG.info("-------------------------------");
        for (Statement question : repository.findAll()) {
            LOG.info(question.toString());
        }
        LOG.info("");

    }
}
