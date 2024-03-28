package com.dogfood.demo.springsecurity.example.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongfupeng
 */
@RestController
public class MyController {

    @GetMapping("/endpoint")
    public ResponseEntity<String> endpoint() {
        return ResponseEntity.ok("endpoint");
    }
}
