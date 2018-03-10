package com.sample.scrumboard.Repositories;

import com.sample.scrumboard.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailEquals(String string);
}
