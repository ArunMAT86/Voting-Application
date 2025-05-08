package com.revature.voting_app.service;


import com.revature.voting_app.entity.OptionVote;
import com.revature.voting_app.entity.Poll;
import com.revature.voting_app.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;

    public Poll createPoll(Poll poll){
        return this.pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
     return this.pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        Optional<Poll> OptionalPoll =  this.pollRepository.findById(id);
        return OptionalPoll;
    }

    public void vote(Long pollId, int optionIndex) {
        Poll pollDb = pollRepository.findById(pollId).orElseThrow(()-> new RuntimeException("No Id found for vote"));
        List<OptionVote> Options = pollDb.getOptions();
        if(optionIndex < 0 || optionIndex >= Options.size()){
            throw new IllegalArgumentException("No Valid selection");
        }
        OptionVote selectedOption = Options.get(optionIndex);
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);
        pollRepository.save(pollDb);
    }
}
