import { Component, OnInit } from '@angular/core';
import { PollService } from '../poll.service';
import { Poll, PollRequest } from '../poll.models';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { errorContext } from 'rxjs/internal/util/errorContext';

@Component({
  selector: 'app-poll',
  imports: [CommonModule,FormsModule],
  templateUrl: './poll.component.html',
  styleUrl: './poll.component.css'
})
export class PollComponent implements OnInit{


addOption() {
this.newPoll.options.push({optionText: '', voteCount: 0});
}
  


  newPoll: PollRequest = {
      question: '',
      options: [
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0}
    ]
  }

  polls: Poll[] = [];

  constructor(private pollService: PollService){
  }
  ngOnInit(): void {
    this.loadPoll();
  }

  loadPoll(){
    this.pollService.getPoll().subscribe({
      next: (createdPoll)=>{
        this.polls = createdPoll;
      },
      error:(error)=>{
        console.log(this.newPoll);
        console.log('Error while fetching data', error);
        
      }
    });
  }

  trackByIndex(index:number): number{
    return index;
  }

  createPoll() {
    this.pollService.createPoll(this.newPoll).subscribe({
      next: (data) =>{
        this.polls.push(data);
        this.resetPoll();
        console.log(this.newPoll);
        console.log(data);
      },
      error: (error)=>{
        console.log(' Error', error);
      }
    });
    }

    resetPoll(){
      this.newPoll ={
      question: '',
      options: [
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0},
      {optionText: '', voteCount: 0}
    ]
      }
    }

    vote(pollId: number | undefined, optionIndex: number) {
      this.pollService.vote(pollId, optionIndex).subscribe({
        next: () =>{
          const poll = this.polls.find(p => p.id === pollId);
          if(poll){
            poll.options[optionIndex].voteCount++;
          }
        },
        error: (error)=>{
          console.log('Error while voting', error);
        }
      });
      }
}
