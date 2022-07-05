package com.example.demo.services;

import com.example.restwrapper.services.RestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.function.Supplier;

@Service
@Slf4j
public class TestService {
    private final RestWrapper restWrapper;

    public TestService(final RestWrapper restWrapper) {
        this.restWrapper = restWrapper;
    }

    public String executeRest(final String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        log.info("url: " + url);
        log.info("status: " + response.getStatusCode());
        return response.getBody();
    }

    public String executeRestWithRetry(final String url) {
        Supplier<String> supplier = ()-> executeRest(url);
        return restWrapper.executeRestWithRetry(supplier, "users");
    }

    public String executeRestWithCircuitBreaker(final String url) {
        Supplier<String> supplier = ()-> executeRest(url);
        return restWrapper.executeRestWithCircuitBreaker(supplier, "users");
    }
}
