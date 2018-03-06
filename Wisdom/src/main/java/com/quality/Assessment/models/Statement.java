package com.quality.Assessment.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sentence;

    public Statement(){
    }

    public Statement(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", sentence='" + sentence + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
