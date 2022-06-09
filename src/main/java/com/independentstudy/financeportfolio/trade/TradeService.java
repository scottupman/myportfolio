package com.independentstudy.financeportfolio.trade;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class TradeService
{
    private final TradeRepository tradeRepository;

    public List<ProfitLossOnAssets> getProfitLoss(String username)
    {
        return tradeRepository.findProfitLossByUsername(username);
    }
}
