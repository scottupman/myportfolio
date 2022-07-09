package com.independentstudy.financeportfolio.user;

import com.independentstudy.financeportfolio.exceptions.NotEnoughBuyingPowerException;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class UserService
{
    private final UserRepository userRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public List<String> getAllUsers()
    {
        return userRepository.getAllUsernames();
    }

    public List<String> getAllEmails()
    {
        return userRepository.getAllEmails();
    }

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

    @Transactional
    public void withdraw(String username, BigDecimal value) throws NotEnoughBuyingPowerException
    {
        User user = getUser(username);
        BigDecimal userCash = user.getCashValue();

        // if userCash < value
        if (userCash.compareTo(value) == -1)
        {
            df.setRoundingMode(RoundingMode.DOWN);
            throw new NotEnoughBuyingPowerException("You only have $" + df.format(userCash));
        }

        modifyCashValue(username, value.negate());
    }

    public BigDecimal getCashValue(String username)
    {
        return userRepository.findCashValueByUsername(username);
    }
}
