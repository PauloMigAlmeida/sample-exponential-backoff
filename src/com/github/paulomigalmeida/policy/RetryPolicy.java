/*
 * The MIT License
 *
 * Copyright (c) 2017- 2017 Paulo Miguel Almeida Rodenas <paulo.miguel.almeida.rodenas(at)gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.paulomigalmeida.policy;

import java.util.logging.Logger;

/**
 * Class used to calculate the amount of time among the attempts and to control wheter of not we have exceeded
 * the number of attempts as well
 *
 * Created by Paulo Miguel Almeida - @PauloMigAlmeida on 4/16/2017.
 */

public class RetryPolicy {

    /**
     * reference to Logger instance
     */
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    /**
     * instance variable used to determine the maximum number of attempts this algorithm will accept.
     */
    private final int maxNumberOfRetries;

    /**
     * constant value used to determine the increment block between each attempt.
     */
    private final static long increment = 100L;

    /**
     * Default constructor
     * @param maxNumberOfRetries - the maximum amount of attempts this program may try.
     */
    public RetryPolicy(int maxNumberOfRetries){
        this.maxNumberOfRetries = maxNumberOfRetries;
    }

    /**
     * Method used to block the current thread until it's allowed to try the operation again based on the attempt
     * number specified
     *
     * @param attemptNumber - the amount of times you have already attempted
     * @throws ExceededNumberOfRetriesException- The number of attempts have exceeded that maximum number specified
     * in the constructor
     * @throws InterruptedException - The Thread.sleep method couldn't finish its execution for any reason listed in
     * the official JDK documentation
     */
    public void waitUntilNextTry(int attemptNumber) throws ExceededNumberOfRetriesException, InterruptedException {
        if (attemptNumber > maxNumberOfRetries)
            throw new ExceededNumberOfRetriesException();
        else
            Thread.sleep(calculateTimeToWait(attemptNumber));
    }

    /**
     * Method used to calculate how long time we should wait until we can try the operation again
     * @param attemptNumber - the amount of times you have already attempted
     * @return time in milliseconds the program have to wait
     */
    private long calculateTimeToWait(int attemptNumber){
        long timeToWait = ((long)Math.pow(2.0,attemptNumber) * increment);
        this.logger.info(String.format("attempt: %d - calculated time" +
                " for waiting until next attempt is: %d milliseconds", attemptNumber, timeToWait));
        return timeToWait;
    }

    /**
     * Method used to determine whether or not it has attempts left
     * @param attemptNumber - the amount of times you have already attempted
     * @return true if it does or false if it doesn't
     */
    public boolean hasNextTry(int attemptNumber){
        return attemptNumber < maxNumberOfRetries;
    }

}
