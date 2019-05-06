package com.authright.happyprime.utils;

import org.springframework.stereotype.Component;

import java.util.HashSet;

public class NumberUtils {
    public static boolean isHappy(long num){
        if (num <= 0){
            return false;
        }
        HashSet<Long> set = new HashSet<>();
        while(set.add(num)){
            num = sumsquares(num);
            if (num == 1){
                return true;
            }
        }
        return false;
    }

    public static int sumsquares(long num){
        int sum = 0;
        while(num > 0){
            sum += (num%10)*(num%10);
            num = num/10;
        }
        return sum;
    }

    public static boolean isPrime(long n){
        if (n <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }
}
