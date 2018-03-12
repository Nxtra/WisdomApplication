package com.sample.scrumboard.Models;

import javax.persistence.*;

@Entity
public class UserStory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String story;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User owner;

    public UserStory(){
    }

    public UserStory(String story, User owner) {
        this.story = story;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
