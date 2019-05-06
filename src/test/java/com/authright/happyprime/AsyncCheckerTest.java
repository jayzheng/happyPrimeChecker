package com.authright.happyprime;



import com.authright.happyprime.utils.AsyncChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static  org.junit.Assert.*;

/**
 * Created by jayzheng on 5/5/19.
 */

@RunWith(Parameterized.class)
public class AsyncCheckerTest {


    private long inputNum;
    private boolean isHappyPrime;

    public AsyncCheckerTest(int inputNum, boolean isHappyPrime) {
        this.inputNum = inputNum;
        this.isHappyPrime = isHappyPrime;
    }

    @Parameterized.Parameters(name = "{index}: input number:{0},isHappyPrime: {1} ")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, false},
                {2, false},
                {19, true},
                {20, false},
                {167, true},
                {409, true},
                {487, true},
                {1972728181, false}
        });
    }


    @Test
    public void testIsHappyPrime() throws ExecutionException, InterruptedException {
        assertEquals(AsyncChecker.isHappyPrime(this.inputNum), this.isHappyPrime);
    }
}
