package com.authright.happyprime.services.impl;

import com.authright.happyprime.services.HttpClientService;
import com.authright.happyprime.services.RandomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class RandomNumberServiceImpl implements RandomNumberService {

    @Value("${randomServiceUrl}")
    private String randomServiceUrl;

    @Autowired
    HttpClientService httpClientService;

    public long getNumber(){
        ResponseEntity<String> response = httpClientService.doGetString(randomServiceUrl);
        try{
            long number = Long.parseLong(response.getBody().trim());
            return number;
        }
        catch (NumberFormatException e){
            return 0;
        }
    }
}
