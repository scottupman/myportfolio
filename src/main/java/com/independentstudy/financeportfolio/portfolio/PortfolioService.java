package com.independentstudy.financeportfolio.portfolio;

import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Data
public class PortfolioService
{
    private final PortfolioRepository portfolioRepository;

    public void createNewPortfolio(Portfolio portfolio)
    {
        portfolioRepository.save(portfolio);
    }

    @Transactional
    public void modifyCashValue(BigDecimal cashValue, Integer id)
    {
        Portfolio portfolio = getPortfolio(id);
        portfolio.setCashValue(cashValue);
    }



    public Portfolio getPortfolio(Integer id)
    {
        Optional<Portfolio> portfolioOptional = portfolioRepository.findById(id);
        if (portfolioOptional.isPresent())
            return portfolioOptional.get();
        else throw new IllegalStateException("user with id " + id + " does not exist");
    }
}
