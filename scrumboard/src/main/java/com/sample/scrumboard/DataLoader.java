package com.sample.scrumboard;

import com.sample.scrumboard.repositories.UserRepository;
import com.sample.scrumboard.models.User;
import com.sample.scrumboard.repositories.UserStoryRepository;
import com.sample.scrumboard.models.UserStory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private UserRepository userRepository;
    private UserStoryRepository userStoryRepository;

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    public DataLoader(UserRepository userRepository, UserStoryRepository userStoryRepository) {
        this.userRepository = userRepository;
        this.userStoryRepository = userStoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        addUsers();
        addUserStories();
    }

    private void addUsers(){
        userRepository.save(new User("testy", "mc donald", "ty", "password", "testy.mcdonald@email.com"));
        userRepository.save(new User("testoo", "mc daniels", "too", "passcode", "testoo.mcdaniels@email.com"));

        LOG.info("Users found with findAll():");
        LOG.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            LOG.info(user.toString());
        }
        LOG.info("");
    }

    private void addUserStories(){
        userStoryRepository.save(new UserStory("As a <type of user>, I can <some goal> so that <some reason>",userRepository.getOne(1L)));
        userStoryRepository.save(new UserStory("As a <type of user>, I want to <some goal> so that <some reason>.", userRepository.getOne(1l)));
        userStoryRepository.save(new UserStory("As a developer, I want to use SonarLint so that it can help me " +
                "keeping my code clean", userRepository.getOne(2L)));

        LOG.info("User stories found with findAll():");
        LOG.info("-------------------------------");
        for (UserStory userStory : userStoryRepository.findAll()) {
            LOG.info(userStory.toString());
        }
        LOG.info("");
    }
}
