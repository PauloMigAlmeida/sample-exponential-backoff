package com.company.policy;

/**
 * Created by mpaulo on 4/16/2017.
 */
public class ExceededNumberOfRetriesException extends Exception {

    private static final String message = "Number of retries has exceeded the established value";

    public ExceededNumberOfRetriesException() {
        super(message);
    }

    public ExceededNumberOfRetriesException(Throwable cause) {
        super(message, cause);
    }
}
