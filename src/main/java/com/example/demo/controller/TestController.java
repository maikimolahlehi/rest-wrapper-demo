package com.example.demo.controller;

import com.example.demo.services.TestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/test", produces = "application/json")
public class TestController {

    private final TestService testService;

    public TestController(final TestService testService) {
        this.testService = testService;
    }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<Object> executeRest(final @RequestParam("url") String url) throws Exception {
        return new ResponseEntity<>(testService.executeRest(url), HttpStatus.OK);
    }

    @GetMapping("/retry")
    public @ResponseBody
    ResponseEntity<Object> executeRestWithRetry(final @RequestParam("url") String url) throws Exception {
        return new ResponseEntity<>(testService.executeRestWithRetry(url), HttpStatus.OK);
    }

    @GetMapping("/circuit-breaker")
    public @ResponseBody
    ResponseEntity<Object> executeRestWithCircuitBreaker(final @RequestParam("url") String url) throws Exception {
        return new ResponseEntity<>(testService.executeRestWithCircuitBreaker(url), HttpStatus.OK);
    }

    // mvn spring-boot:run -Dspring-boot.run.profiles=local
}
