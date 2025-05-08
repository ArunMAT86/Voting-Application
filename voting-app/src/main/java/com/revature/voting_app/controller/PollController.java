package com.revature.voting_app.controller;


import com.revature.voting_app.Exception.ApplicationError;
import com.revature.voting_app.Exception.ErrorResponse;
import com.revature.voting_app.entity.Poll;
import com.revature.voting_app.request.Vote;
import com.revature.voting_app.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "api")
@CrossOrigin(origins = "http://localhost:4200")
public class PollController {

    @Autowired
    PollService pollService;

    @PostMapping(path = "/polls")
    private ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        try {
            Poll savedPoll = pollService.createPoll(poll);
            if (savedPoll != null) {
                return ResponseEntity.ok(savedPoll);
            } else {
                throw new ApplicationError("Poll Creation Fail");
            }
        } catch (ApplicationError e) {
            ErrorResponse response = new ErrorResponse(e.getMessage(), "Error", LocalDateTime.now());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @GetMapping(path = "polls")
    private ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> allPolls = this.pollService.getAllPolls();
        return ResponseEntity.ok(allPolls);
    }

    @GetMapping(path = "polls/{id}")
    private ResponseEntity<?> getPollById(@PathVariable Long id) {
        try {
            Poll poll = pollService.getPollById(id).orElseThrow(()-> new ApplicationError("No poll found with given id "+ id));
            return ResponseEntity.ok(poll);
        }catch (ApplicationError e){
            ErrorResponse response = new ErrorResponse("No ID found",e.getMessage(), LocalDateTime.now());
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/polls/vote")
    public void vote(@RequestBody Vote vote){
        pollService.vote(vote.getPollId(),vote.getOptionIndex());
    }
}
