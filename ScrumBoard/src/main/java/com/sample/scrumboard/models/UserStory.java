package com.sample.scrumboard.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String story;

    public UserStory(){
    }

    public UserStory(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "id=" + id +
                ", story='" + story + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
