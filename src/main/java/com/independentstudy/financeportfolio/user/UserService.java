package com.independentstudy.financeportfolio.user;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class UserService
{
    private final UserRepository userRepository;

    public User getUser(String username)
    {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new IllegalStateException("user with username " + username + " was not found");
    }
    public void addUser(User user)
    {
        userRepository.save(user);
    }

    public boolean userExists(String username)
    {
        boolean userExists = userRepository.existsById(username);
        return userExists;
    }

    public boolean emailExists(String email)
    {
        List<User> users = userRepository.findByEmailIgnoreCase(email);
        if (users.isEmpty())
            return false;
        else
            return true;
    }
    public boolean userValid(String username, String password)
    {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isPresent())
        {
            User storedUser = userOptional.get();
            if (Objects.equals(password, storedUser.getPassword()))
                return true;
        }
        return false;
    }
    @Transactional
    public void modifyCashValue(String username, BigDecimal value)
    {
        User user = getUser(username);
        user.setCashValue(user.getCashValue().add(value));
    }

    public BigDecimal getCashValue(String username)
    {
        return userRepository.findCashValueByUsername(username);
    }
}
