package com.system.management.controller;


import com.system.management.entity.ErrorResponse;
import com.system.management.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ErrorController {
    private final ErrorService service;

    @GetMapping("/errors")
    public ResponseEntity<List<ErrorResponse>> findCourses() {

            return new ResponseEntity<>(service.findAll(), HttpStatus.OK);

    }
}
