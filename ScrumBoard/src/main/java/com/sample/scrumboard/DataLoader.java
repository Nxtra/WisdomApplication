package com.sample.scrumboard;

import com.sample.scrumboard.Repositories.UserStoryRepository;
import com.sample.scrumboard.models.UserStory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserStoryRepository repository;

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    public DataLoader(UserStoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        repository.save(new UserStory("As a <type of user>, I can <some goal> so that <some reason>."));
        repository.save(new UserStory("As a <type of user>, I want to <some goal> so that <some reason>."));
        repository.save(new UserStory("As a developer, I want to use SonarLint so that it can help me " +
                "keeping my code clean"));

        LOG.info("User stories found with findAll():");
        LOG.info("-------------------------------");
        for (UserStory question : repository.findAll()) {
            LOG.info(question.toString());
        }
        LOG.info("");

    }
}
