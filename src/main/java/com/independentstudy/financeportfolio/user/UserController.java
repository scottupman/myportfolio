package com.independentstudy.financeportfolio.user;

import com.independentstudy.financeportfolio.exceptions.InvalidCredentialsException;
import com.independentstudy.financeportfolio.exceptions.NotEnoughBuyingPowerException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("user")
@Data
public class UserController
{
    private final UserService userService;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @GetMapping
    public ResponseEntity getAllUsers()
    {
        List<String> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/email")
    public ResponseEntity getAllEmails()
    {
        List<String> allEmails = userService.getAllEmails();
        return ResponseEntity.ok(allEmails);
    }

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
    public ResponseEntity login(@RequestBody User user) throws InvalidCredentialsException
    {
        if (userService.userValid(user.getUsername(), user.getPassword()))
            return ResponseEntity.ok("User " + user.getUsername() + " has logged in");
        else
            throw new InvalidCredentialsException("Incorrect username or password");
    }

    @PutMapping("{username}/deposit/{value}")
    public ResponseEntity deposit(@PathVariable String username, @PathVariable BigDecimal value)
    {
        df.setRoundingMode(RoundingMode.DOWN);

        userService.modifyCashValue(username, value);
        return ResponseEntity.ok("Deposited $" + df.format(value));
    }

    @PutMapping("{username}/withdraw/{value}")
    public ResponseEntity withdraw(@PathVariable String username, @PathVariable BigDecimal value) throws NotEnoughBuyingPowerException
    {
        df.setRoundingMode(RoundingMode.DOWN);

        userService.withdraw(username, value);
        return ResponseEntity.ok("Withdrew $" + df.format(value));
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
