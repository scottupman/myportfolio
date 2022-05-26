package com.independentstudy.financeportfolio.portfolio;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("portfolio")
@Data
public class PortfolioController
{
    private final PortfolioService portfolioService;

    @GetMapping("{id}")
    public ResponseEntity getPortfolio(@PathVariable Integer id)
    {
        try
        {
            Portfolio portfolio = portfolioService.getPortfolio(id);
            return ResponseEntity.ok(portfolio);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @PutMapping("{id}/cash")
    public ResponseEntity modifyCashValue(@RequestParam BigDecimal cashValue, @PathVariable Integer id)
    {
        try
        {
            portfolioService.modifyCashValue(cashValue, id);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        return ResponseEntity.ok("Modified cash value to " + cashValue + " for user with id " + id);
    }
}
