package com.revature.voting_app.entity;


import jakarta.persistence.Embeddable;

@Embeddable
public class OptionVote {

    private String optionText;

    private Long voteCount = 0L;

    public OptionVote() {
    }

    public OptionVote(String options, Long voteCount) {
        this.optionText = options;
        this.voteCount = voteCount;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
