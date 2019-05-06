package com.authright.happyprime.utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by jayzheng on 5/5/19.
 */
public class AsyncChecker {
    public static boolean isHappyPrime(long number) throws ExecutionException, InterruptedException {

        CompletableFuture<Boolean> isPrimeResult =
                CompletableFuture.supplyAsync(() -> NumberUtils.isPrime(number));
        CompletableFuture<Boolean> isHappyResult =
                CompletableFuture.supplyAsync(() -> NumberUtils.isHappy(number));

        boolean asyncIsPrimeResult = isPrimeResult.get().booleanValue();
        boolean asycIsHappyResult = isHappyResult.get().booleanValue();

        return asycIsHappyResult && asyncIsPrimeResult;
    }
}
