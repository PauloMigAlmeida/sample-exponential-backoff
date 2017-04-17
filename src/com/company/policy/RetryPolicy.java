package com.company.policy;

import java.util.logging.Logger;

public class RetryPolicy {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private final int maxNumberOfRetries;
    private final static long increment = 100L;

    public RetryPolicy(int maxNumberOfRetries){
        this.maxNumberOfRetries = maxNumberOfRetries;
    }

    public void waitUntilNextTry(int attemptNumber) throws ExceededNumberOfRetriesException, InterruptedException {
        if (attemptNumber > maxNumberOfRetries)
            throw new ExceededNumberOfRetriesException();
        else
            Thread.sleep(calculateTimeToWait(attemptNumber));
    }

    private long calculateTimeToWait(int attemptNumber){
        long timeToWait = ((long)Math.pow(2.0,attemptNumber) * increment);
        this.logger.info(String.format("attempt: %d - calculated time" +
                " to wait until next attempt is: %d milliseconds", attemptNumber, timeToWait));
        return timeToWait;
    }

    public boolean hasNextTry(int attemptNumber){
        return attemptNumber < maxNumberOfRetries;
    }

}
