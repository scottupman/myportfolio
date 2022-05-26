package com.independentstudy.financeportfolio.user;

import com.independentstudy.financeportfolio.portfolio.Portfolio;
import com.independentstudy.financeportfolio.portfolio.PortfolioService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
public class UserService
{
    private final UserRepository userRepository;
    private final PortfolioService portfolioService;

    public void addUser(User user)
    {
        userRepository.save(user);
        Portfolio portfolio = new Portfolio(user.getUsername());
        portfolioService.createNewPortfolio(portfolio);
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
}
