# sample-exponential-backoff

## What is exponential backoff?
Numerous components on a network, such as DNS servers, switches, load balancers, and others can generate errors anywhere in the life of a given request. The usual technique for dealing with these error responses in a networked environment is to implement retries in the client application. This technique increases the reliability of the application and reduces operational costs for the developer. 

## What is the goal of this sample?
The idea behind this source code is to provide a working example of how you can implement this technique in your projects to increase the reliability of your distributed systems. Technically speaking, all SDKs should have this implemented by default, however, that's quite far from the reality.

## Output
```{Shell}
"C:\Program Files\Java\jdk1.8.0_121\bin\java" -Dfile.encoding=UTF-8 -classpath ".;exponential-backoff\out\production\exponential-backoff" com.github.paulomigalmeida.Main

Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.operation.SampleOperation <init>
INFO: Max number of retries is: 10 and the sample operation is going to succeed on the attempt: 7
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 1 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 1 - calculated time for waiting until next attempt is: 200 milliseconds
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 2 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 2 - calculated time for waiting until next attempt is: 400 milliseconds
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 3 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:15 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 3 - calculated time for waiting until next attempt is: 800 milliseconds
Apr 18, 2017 2:00:16 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 4 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:16 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 4 - calculated time for waiting until next attempt is: 1600 milliseconds
Apr 18, 2017 2:00:18 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 5 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:18 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 5 - calculated time for waiting until next attempt is: 3200 milliseconds
Apr 18, 2017 2:00:21 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 6 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:21 PM com.github.paulomigalmeida.policy.RetryPolicy calculateTimeToWait
INFO: attempt: 6 - calculated time for waiting until next attempt is: 6400 milliseconds
Apr 18, 2017 2:00:27 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: attempt: 7 - I'm pretending that I'm doing a meaningful operation
Apr 18, 2017 2:00:27 PM com.github.paulomigalmeida.operation.SampleOperation performOperation
INFO: Operation has successfully completed

Process finished with exit code 0
```

## Contributing 

You're encouraged to contribute to sample-exponential-backoff. Fork the code from https://github.com/PauloMigAlmeida/sample-exponential-backoff and submit pull requests.

Make sure you're following the [contributing guidelines](https://github.com/PauloMigAlmeida/sample-exponential-backoff/blob/master/CONTRIBUTING.md) for this project.