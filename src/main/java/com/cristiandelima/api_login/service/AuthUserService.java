package com.cristiandelima.api_login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cristiandelima.api_login.DTO.AuthUserDTO;
import com.cristiandelima.api_login.exception.UserNotFoundException;
import com.cristiandelima.api_login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthUserService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthUserDTO authUserDTO) throws AuthenticationException {
        var user = userRepository.findByUsername(authUserDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username or Password is incorrect"));

        var passwordMatches = passwordEncoder.matches(authUserDTO.getPassword(), user.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException("Username or Password is incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("criss")
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(15)))
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return token;
    }
}
