package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.User;
import com.example.demo.DTO.UserPostDTO;
import com.example.demo.Models.UserType;
import com.example.demo.Services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService userService;

    // =====================================
    // GET ALL USERS
    // =====================================
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    // =====================================
    // GET USER BY ID
    // =====================================
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> user = userService.findByID(id);

        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =====================================
    // GET USER BY EMAIL
    // =====================================
    @GetMapping("/user/findByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestParam(required = false) String email) {

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    // =====================================
    // CREATE NEW USER
    // =====================================
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UserPostDTO newUserDTO) {

        if (newUserDTO == null ||
            newUserDTO.getName() == null ||
            newUserDTO.getEmail() == null ||
            newUserDTO.getPassword() == null ||
            newUserDTO.getUserType() == null ||
            newUserDTO.getUserType() == UserType.NONE) {

            return ResponseEntity.badRequest().body("Invalid user data");
        }

        User newUser = new User(
                newUserDTO.getName(),
                newUserDTO.getEmail(),
                newUserDTO.getPassword(),
                newUserDTO.getUserType()
        );

        userService.addUser(newUser);

        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }

    // =====================================
    // DELETE USER
    // =====================================
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }
}