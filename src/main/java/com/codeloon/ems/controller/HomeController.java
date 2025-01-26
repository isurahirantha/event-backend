package com.codeloon.ems.controller;

import com.codeloon.ems.entity.User;
import com.codeloon.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String getHome(){
        return "Hello World";
    }

    @GetMapping("/clients")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
