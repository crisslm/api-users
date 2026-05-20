package com.cristiandelima.api_login.service;

import com.cristiandelima.api_login.exception.UserFoundException;
import com.cristiandelima.api_login.exception.UserNotFoundException;
import com.cristiandelima.api_login.models.User;
import com.cristiandelima.api_login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User userEntity){
        this.userRepository
                .findByUsernameOrEmail(userEntity.getUsername(), userEntity.getEmail())
                .ifPresent((user)->{
                    throw new UserFoundException();
                });


        var password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        return userRepository.save(userEntity);
    }

    public User getUser(UUID id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            throw new UserNotFoundException();
        } else{
            return users;
        }
    }

    public User updateUser(UUID id, User userEntity){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(user);
    }

    public User deleteUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        return user;
    }
}
