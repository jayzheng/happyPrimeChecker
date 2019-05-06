package com.authright.happyprime.controllers;

import com.authright.happyprime.services.RandomNumberService;
import com.authright.happyprime.utils.NumberUtils;
import com.authright.happyprime.utils.AsyncChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by leizhang on 5/2/19.
 */
@RestController()
@RequestMapping(value = {"/v1/"})
public class HappyController {

    @Autowired
    RandomNumberService randomNumberService;


    @GetMapping(value = {"/isHappyprime"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> isHappyPrime() {
        long number = randomNumberService.getNumber();
        if (number > 0){
            return happyPrimeResponse(number);
        }
        else{
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("error", "Error getting random number.");
                    }},
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping(value = {"/isHappyprime/{number}"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> isHappyPrime(@PathVariable(value = "number") long number) {
        return happyPrimeResponse(number);
    }


    @GetMapping(value = {"/isHappyprimeAsync"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> isHappyPrimeAsync( ) {
        long number = randomNumberService.getNumber();
        if (number > 0){
            return happyPrimeAsync(number);
        }
        else{
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("error", "Error getting random number.");
                    }},
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @GetMapping(value = {"/isHappyprimeAsync/{number}"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> isHappyPrimeAsync(@PathVariable(value = "number") long number) {
        return happyPrimeAsync(number);
    }

    private  ResponseEntity<Map<String, Object>> happyPrimeResponse(long number) {
        boolean isHappy = NumberUtils.isHappy(number);
        boolean isPrime = NumberUtils.isPrime(number);
        return new ResponseEntity<>(
                new HashMap<String, Object>() {{
                    put("number", number);
                    put("isHappyPrime", isHappy && isPrime);
                }},
                HttpStatus.OK
        );
    }

    public ResponseEntity<Map<String, Object>> happyPrimeAsync(long number) {
        try {
            boolean isHappyPrime = AsyncChecker.isHappyPrime(number);
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("number", number);
                        put("isHappyPrime", isHappyPrime);
                    }},
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new HashMap<String, Object>() {{
                        put("number", number);
                        put("Error", "Run Time Exception");
                    }},
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
