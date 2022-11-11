package com.example.logistics.controller;

import com.example.logistics.model.JwtRequest;
import com.example.logistics.model.JwtResponse;
import com.example.logistics.model.User;
import com.example.logistics.service.UserService;
import com.example.logistics.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class AuthController {
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String home() {
        return "Welcome home";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getEmail(),
                            jwtRequest.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();

            final String accessToken =
                jwtUtility.getGeneratedToken(user);

            JwtResponse authResponse = new JwtResponse(user.getName(), user.getEmail(), accessToken);

            System.out.println(authResponse);

            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
