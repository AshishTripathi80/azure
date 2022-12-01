package org.nagarro.product.catalouge.controller;

import org.nagarro.product.catalouge.model.JwtResponse;
import org.nagarro.product.catalouge.exception.UserNotFoundException;
import org.nagarro.product.catalouge.jwtutility.JwtUtil;
import org.nagarro.product.catalouge.model.JwtRequest;
import org.nagarro.product.catalouge.model.User;
import org.nagarro.product.catalouge.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user-service")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @CrossOrigin("http://localhost:4200")

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws UserNotFoundException {
        User user = userRepo.findByUsername(jwtRequest.getUsername());
        if (user == null) {
            throw new UserNotFoundException("Invalid Username");
        } else if (!user.getPassword().equals(jwtRequest.getPassword())) {
            throw new UserNotFoundException("Invalid Credentials");
        } else {
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(user.getUsername(), token));
        }
    }
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println(user.toString());
        userRepo.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(user.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
