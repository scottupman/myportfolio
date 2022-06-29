package com.independentstudy.financeportfolio.user;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("user")
@Data
public class UserController
{
    private final UserService userService;

    @GetMapping("{username}")
    public ResponseEntity getUser(@PathVariable String username)
    {
        try
        {
            User user = userService.getUser(username);
            return ResponseEntity.ok(user);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @GetMapping("{username}/cash")
    public ResponseEntity getCashValue(@PathVariable String username)
    {
        try
        {
            BigDecimal cashValue = userService.getCashValue(username);
            return ResponseEntity.ok(cashValue);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @PutMapping
    public ResponseEntity login(@RequestBody User user)
    {
        if (userService.userValid(user.getUsername(), user.getPassword()))
            return ResponseEntity.ok("User " + user.getUsername() + " has logged in");
        else
            return ResponseEntity.badRequest().body("Username or password is invalid");
    }
    @PostMapping
    public ResponseEntity registerUser(@RequestBody User user)
    {
        // check if user exists first
        if (userService.userExists(user.getUsername()))
            return ResponseEntity.badRequest().body("user " + user.getUsername() + " already exists");
        else if (userService.emailExists(user.getEmail()))
        {
            return ResponseEntity.badRequest().body("email " + user.getEmail() + " already exists");
        }
        else
        {
            userService.addUser(user);
            return ResponseEntity.ok("Added user " + user.getUsername() + " to database");
        }
    }
}
