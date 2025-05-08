package com.revature.voting_app.Exception;

public class ApplicationError extends RuntimeException{

    public ApplicationError(String message) {
        super(message);
    }
}
