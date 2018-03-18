package com.sample.scrumboard.repositories;

import com.sample.scrumboard.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailEquals(String string);
}
