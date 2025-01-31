package com.vehiclemanagementsys.vehiclemanagement.service;

import com.vehiclemanagementsys.vehiclemanagement.model.User;
import com.vehiclemanagementsys.vehiclemanagement.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addNewUser(User user) {
        Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());

        if (userByUsername.isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        userRepository.save(user);
        System.out.println(user);
    }

    // Get all User
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    // Get User by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get User by email
    public Optional<User> getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Transactional
    public void updateUser(Long userID, String username, String passwordHash, String role) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userID + " does not exist"));

        // Check if the phone number is changing
        if (username != null && !username.equals(user.getUsername())) {
            Optional<User> customerByUsername = userRepository.findByUsername(username);
            if (customerByUsername.isPresent()) {
                throw new IllegalArgumentException("Phone number already in use by another customer");
            }
        }

        if(username != null && !username.equals(user.getUsername())) {
            user.setUsername(username);
        }

        if(passwordHash != null && !passwordHash.equals(user.getPasswordHash())) {
            user.setPasswordHash(passwordHash);
        }

        if(role != null && !role.equals(user.getRole())) {
            user.setRole(role);
        }

        userRepository.save(user);
    }

    // Delete User by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> authenticate(String username, String password) {
        // Retrieve the user by username
        Optional<User> userByUsername = userRepository.findByUsername(username);

        if (userByUsername.isPresent()) {
            User user = userByUsername.get();

            // Compare the plaintext password with the hashed password
            if (BCrypt.checkpw(password, user.getPasswordHash())) {
                // Authentication successful, return the user
                return Optional.of(user);
            }
        }

        // If username doesn't exist or password doesn't match, return empty
        return Optional.empty();
    }

    public boolean doesAdminExist(){
        return userRepository.countByAdmin() > 0;
    }
}
