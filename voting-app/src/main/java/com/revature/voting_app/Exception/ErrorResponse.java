package com.revature.voting_app.Exception;


import java.time.LocalDateTime;

public class ErrorResponse {

    private LocalDateTime localDateTime;

    private  String details;

    private String error;

    public ErrorResponse(String details, String error, LocalDateTime localDateTime) {
        this.details = details;
        this.error = error;
        this.localDateTime = localDateTime;
    }
}
