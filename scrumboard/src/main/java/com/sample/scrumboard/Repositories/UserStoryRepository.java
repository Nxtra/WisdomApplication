package com.sample.scrumboard.repositories;

import com.sample.scrumboard.models.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryRepository extends JpaRepository<UserStory, Long>{

    UserStory findByStoryEquals(String string);

}
