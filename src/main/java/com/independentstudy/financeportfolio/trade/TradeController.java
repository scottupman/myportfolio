package com.independentstudy.financeportfolio.trade;

import com.independentstudy.financeportfolio.exceptions.SellNonExistentAssetException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/trades")
public class TradeController
{
    private final TradeService tradeService;

    @GetMapping("{username}")
    public ResponseEntity<Object> getTrades(@PathVariable String username)
    {
        try
        {
            List<Trade> trades = tradeService.getTrades(username);
            return ResponseEntity.ok(trades);
        }
        catch(Exception e)
        {
            System.out.println("couldn't query trades for user " + username);
            return null;
        }
    }

    @GetMapping("/profit/{username}")
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

    @PostMapping()
    public ResponseEntity<Object> storeTrade(@RequestBody Trade trade)
    {
        try
        {
            tradeService.storeTrade(trade);
            return ResponseEntity.ok("Trade successful!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
