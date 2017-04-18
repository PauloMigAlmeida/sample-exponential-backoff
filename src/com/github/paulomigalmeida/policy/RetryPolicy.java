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
 * Created by Paulo Miguel Almeida - @PauloMigAlmeida on 4/16/2017.
 */

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
