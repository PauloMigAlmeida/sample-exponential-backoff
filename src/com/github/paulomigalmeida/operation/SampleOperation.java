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

package com.github.paulomigalmeida.operation;

import com.github.paulomigalmeida.policy.ExceededNumberOfRetriesException;
import com.github.paulomigalmeida.policy.RetryPolicy;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Paulo Miguel Almeida - @PauloMigAlmeida on 4/16/2017.
 */
public class SampleOperation {

    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
    private RetryPolicy retryPolicy;
    private Random random = new Random();
    private int whichRetryIsThisGonnaWork;

    public SampleOperation(int maxNumberOfRetries) {
        this.retryPolicy = new RetryPolicy(maxNumberOfRetries);
        this.whichRetryIsThisGonnaWork = random.nextInt(maxNumberOfRetries - 1) + 2;
        this.logger.info(String.format("Max number of retries is: %d " +
                "and the sample operation is going to succeed on the attempt: %d",
                maxNumberOfRetries, whichRetryIsThisGonnaWork));
    }

    public void performOperation() throws ExceededNumberOfRetriesException, InterruptedException {
        int attemptNumber = 1;
        do{
            this.logger.info(String.format("attempt: %d - I'm pretending that " +
                            "I'm doing a meaningful operation", attemptNumber));
            if(attemptNumber != this.whichRetryIsThisGonnaWork){
                retryPolicy.waitUntilNextTry(attemptNumber);
            }else{
                this.logger.info("Operation has successfully completed");
                break;
            }
            attemptNumber++;
        }while(retryPolicy.hasNextTry(attemptNumber));
    }


}
