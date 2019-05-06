package com.authright.happyprime;

import com.authright.happyprime.utils.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static  org.junit.Assert.*;



/**
 * Created by jay zheng on 5/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class NumberUtilTest {


    @Test
    public void shouldReturnCorrectSquareSumPositive() {
        assertEquals(NumberUtils.sumsquares(1), 1);
        assertEquals(NumberUtils.sumsquares(20), 4);
        assertEquals(NumberUtils.sumsquares(1234), 30);
    }

    @Test
    public void shouldReturnCorrectSquareSumNegative() {
        assertEquals(NumberUtils.sumsquares(-1), 0);
    }

    @Test
    public void shouldReturnCorrectSquareSumZero() {
        assertEquals(NumberUtils.sumsquares(0), 0);
    }

    @Test
    public void shouldReturnCorrectIsHappyPositive() {
        assertTrue(NumberUtils.isHappy(1));
        assertTrue(NumberUtils.isHappy(100));
        assertTrue(NumberUtils.isHappy(167));
    }

    @Test
    public void shouldReturnCorrectIsHappyNegative() {
        assertFalse(NumberUtils.isHappy(-1));
    }

    @Test
    public void shouldReturnCorrectIsHappyZero() {
        assertFalse(NumberUtils.isHappy(0));
    }

    @Test
    public void shouldReturnCorrectIsPrimePositive() {
        assertFalse(NumberUtils.isPrime(1));
        assertTrue(NumberUtils.isPrime(2));
        assertFalse(NumberUtils.isPrime(1234567890));
    }

    @Test
    public void shouldReturnCorrectIsPrimeNegative() {
        assertFalse(NumberUtils.isPrime(-1));
    }

    @Test
    public void shouldReturnCorrectIsPrimeZero() {
        assertFalse(NumberUtils.isPrime(0));
    }
}
