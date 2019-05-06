package com.authright.happyprime.services;

import org.springframework.http.ResponseEntity;


public interface HttpClientService {
    ResponseEntity<String> doGetString(String baseurl);
}
