package com.codeloon.ems.controller;


import com.codeloon.ems.entity.User;
import com.codeloon.ems.model.UserBean;
import com.codeloon.ems.repository.UserRepository;
import com.codeloon.ems.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public String getAllEmployees(){
        return "You Received All Employees List";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String saveEmployees(){
        return "You saved a Employee";
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String updateEmployees(){
        return "You updated a Employee";
    }

    @GetMapping("/client/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('CLIENT')")
    public ResponseEntity<UserBean> getUser(@PathVariable String username) {
        UserBean userBean = userService.findByUsername(username);
        return new ResponseEntity<>(userBean, HttpStatus.OK);
    }

    @GetMapping("/clients")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('CLIENT')")
    public ResponseEntity<?> getAllUsers(){
        List<UserBean> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}