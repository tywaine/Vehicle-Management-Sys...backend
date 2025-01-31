package com.vehiclemanagementsys.vehiclemanagement.controller;

import com.vehiclemanagementsys.vehiclemanagement.model.User;
import com.vehiclemanagementsys.vehiclemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(@RequestParam(required = false) Long id) {
        if (id != null) {
            Optional<User> user = userService.getUserById(id);

            if (user.isPresent()) {
                return List.of(user.get());
            } else {
                return new ArrayList<>();
            }
        }

        return userService.getUsers();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody User user) {
        try{
            userService.addNewUser(user);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.authenticate(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/doesAdminExists")
    public ResponseEntity<String> doesAdminExists() {
        if (userService.doesAdminExist()) {
            return ResponseEntity.status(HttpStatus.FOUND).body("Admin exists");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin does not exist");
        }
    }

    @DeleteMapping(path = "delete")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        Optional<User> user = userService.getUserById(id);

        if (user.isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "update/{userID}")
    public ResponseEntity<String> updateUser(
            @PathVariable("userID") Long userID,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String passwordHash,
            @RequestParam(required = false) String role) {

        // Ensure at least one field is provided
        if (username == null && passwordHash == null && role == null) {
            return new ResponseEntity<>("At least one field must be provided for update", HttpStatus.BAD_REQUEST);
        }

        try{
            userService.updateUser(userID, username, passwordHash, role);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
