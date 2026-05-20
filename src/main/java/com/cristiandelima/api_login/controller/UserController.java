package com.cristiandelima.api_login.controller;

import com.cristiandelima.api_login.exception.UserNotFoundException;
import com.cristiandelima.api_login.models.User;
import com.cristiandelima.api_login.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Component
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        try{
            var result = userService.createUser(user);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers(){
        try{
            var result = userService.getAllUsers();
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable UUID id){
        try{
            var result = userService.getUser(id);
            return ResponseEntity.ok().body(result);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @Valid @RequestBody User user){
        try{
            var result = userService.updateUser(id, user);
            return ResponseEntity.ok().body(result);
        }catch(UserNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
        try{
            var result = userService.deleteUser(id);
            return ResponseEntity.ok().body(result);
        }catch(UserNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
