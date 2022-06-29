package com.independentstudy.financeportfolio.trade;

import com.independentstudy.financeportfolio.exceptions.NotEnoughBuyingPowerException;
import com.independentstudy.financeportfolio.exceptions.NotEnoughQuantityException;
import com.independentstudy.financeportfolio.exceptions.SellNonExistentAssetException;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("trades")
public class TradeController
{
    private final TradeService tradeService;

    @GetMapping("{username}")
    public ResponseEntity<Object> getTrades(@PathVariable String username)
    {
        List<Trade> trades = tradeService.getTrades(username);
        return ResponseEntity.ok(trades);
    }

    @GetMapping("/profit/{username}")
    public ResponseEntity<Object> getProfitLoss(@PathVariable String username)
    {
        List<ProfitLossOnAssets> profitLossOfAssets = tradeService.getProfitLoss(username);
        return ResponseEntity.ok(profitLossOfAssets);
    }

    @PostMapping
    public ResponseEntity storeTrade(@RequestBody Trade trade) throws NotEnoughBuyingPowerException, NotEnoughQuantityException, SellNonExistentAssetException
    {
        tradeService.storeTrade(trade);
        return ResponseEntity.ok("Trade successful!");
    }
}
