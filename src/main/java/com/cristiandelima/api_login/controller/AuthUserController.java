package com.cristiandelima.api_login.controller;

import com.cristiandelima.api_login.DTO.AuthUserDTO;
import com.cristiandelima.api_login.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/")
    public ResponseEntity<Object> login(@RequestBody AuthUserDTO authUserDTO){
        try{
            var result = authUserService.execute(authUserDTO);
            return ResponseEntity.ok(result);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
