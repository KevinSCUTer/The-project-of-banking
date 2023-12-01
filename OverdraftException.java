package com.experiment.domain;

// Step 7: Create the OverdraftException Class
// 自定义的异常是必检异常
public class OverdraftException extends Exception {
    // Add a private attribute called deficit that holds a double.
    // Add a public accessor called getDeficit.
    private double deficit;

    public double getDeficit() {
        return deficit;
    }

    // Add a public constructor that takes two arguments: message and deficit.
    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }
}
