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

package com.github.paulomigalmeida;

import com.github.paulomigalmeida.operation.SampleOperation;
import com.github.paulomigalmeida.policy.ExceededNumberOfRetriesException;

import java.util.logging.Logger;

/**
 * CLass used to initialize the program
 *
 * Created by Paulo Miguel Almeida - @PauloMigAlmeida on 4/16/2017.
 */

public class Main {

    /**
     * reference to Logger instance
     */
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
