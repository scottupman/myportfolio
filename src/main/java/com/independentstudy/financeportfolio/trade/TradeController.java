package com.independentstudy.financeportfolio.trade;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Data
@RequestMapping("/trades")
public class TradeController
{
    private final TradeService tradeService;

    @GetMapping("{username}")
    public ResponseEntity<Object> getProfitLoss(@PathVariable String username)
    {
        try
        {
            List<ProfitLossOnAssets> profitLossOfAssets = tradeService.getProfitLoss(username);
            return ResponseEntity.ok(profitLossOfAssets);
        }
        catch(Exception e){
            System.out.println("couldn't query profit/loss for user's " + username + " assets");
            return null;
        }
    }
}
