package com.lab.thread;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadTestController {

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().build();
    }
}
