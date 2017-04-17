package com.company.operation;

import com.company.policy.ExceededNumberOfRetriesException;
import com.company.policy.RetryPolicy;

import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by mpaulo on 4/16/2017.
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
