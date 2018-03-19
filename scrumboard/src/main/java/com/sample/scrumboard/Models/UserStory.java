package com.sample.scrumboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class UserStory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "UserStoryId", nullable = false, updatable = false)
    private Long id;

    private String story;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonIgnore
    private User owner;

    public UserStory(){
    }

    public UserStory(String story, User owner) {
        this.story = story;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "id=" + id +
                ", story='" + story + '\'' +
                ", owner=" + owner +
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
