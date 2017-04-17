package com.company;

import com.company.operation.SampleOperation;
import com.company.policy.ExceededNumberOfRetriesException;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        SampleOperation sampleOperation = new SampleOperation(10);
        try {
            sampleOperation.performOperation();
        } catch (ExceededNumberOfRetriesException e) {
            logger.warning("If you reached this exception is because you have exceeded your retry policy " +
                    "attempts");
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.warning("If you reached this exception is becausethe Thread.sleeps couldn't complete" +
                    " successfully. There are dozens of reasons why this would happen, so please refer to the " +
                    "official documentation");
            e.printStackTrace();
        }
    }
}
