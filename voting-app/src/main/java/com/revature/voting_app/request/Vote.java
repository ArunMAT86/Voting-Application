package com.revature.voting_app.request;



public class Vote {

    private Long pollId;

    private int optionIndex;

    public Vote() {
    }

    public Vote(int optionIndex, Long pollId) {
        this.optionIndex = optionIndex;
        this.pollId = pollId;
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(int optionIndex) {
        this.optionIndex = optionIndex;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }
}
