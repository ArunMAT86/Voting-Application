package com.revature.voting_app.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String question;

    @ElementCollection
    private List<OptionVote> options = new ArrayList<>();

    public Poll() {
    }

    public Poll(Long id, List<OptionVote> options, String question) {
        this.id = id;
        this.options = options;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OptionVote> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVote> options) {
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}


