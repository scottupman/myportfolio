package com.independentstudy.financeportfolio.trade;

import com.independentstudy.financeportfolio.asset.Asset;
import com.independentstudy.financeportfolio.asset.AssetService;
import com.independentstudy.financeportfolio.exceptions.SellNonExistentAssetException;
import com.independentstudy.financeportfolio.user.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Data
public class TradeService
{
    private final TradeRepository tradeRepository;
    private final AssetService assetService;
    private final UserService userService;

    public List<ProfitLossOnAssets> getProfitLoss(String username)
    {
        return tradeRepository.findProfitLossByUsername(username);
    }

    public void storeTrade(Trade trade) throws SellNonExistentAssetException {
        Asset asset = assetService.getAsset(trade.getUsername(), trade.getSymbol());
        if (asset == null)
        {
            if (trade.getType().equalsIgnoreCase("sell"))
                throw new SellNonExistentAssetException("can't sell asset " + trade.getSymbol() + " since that user doesn't own it");
            String username = trade.getUsername();
            String symbol = trade.getSymbol();
            String name = trade.getName();
            String type = trade.getSecurityType();
            double quantity = trade.getQuantity();
            Asset newAsset = new Asset(username, symbol, name, type, quantity);
            tradeRepository.save(trade);
            assetService.storeAsset(newAsset);
        }
        else
        {
            String username = trade.getUsername();
            BigDecimal price = trade.getPrice();
            double quantity = trade.getQuantity();
            BigDecimal buyingPower = userService.getUser(username).getCashValue();
            BigDecimal totalPrice = price.multiply(new BigDecimal(quantity));

            if (trade.getType().equalsIgnoreCase("buy"))
            {
                int res = buyingPower.compareTo(totalPrice);
                if (res == -1)
                    throw new ArithmeticException("Not enough buying power");

                tradeRepository.save(trade);
                assetService.modifyQuantity(asset, quantity);
                userService.modifyCashValue(username, totalPrice.negate());
            }

            else if (trade.getType().equalsIgnoreCase("sell"))
            {
                if (asset.getQuantity() < trade.getQuantity())
                    throw new ArithmeticException("User only owns " + asset.getQuantity() + " of " + asset.getSymbol());

                tradeRepository.save(trade);
                assetService.modifyQuantity(asset, -quantity);
                userService.modifyCashValue(username, totalPrice);
            }
        }

    }

    public List<Trade> getTrades(String username)
    {
        return tradeRepository.findTradesByUsername(username);
    }
}
