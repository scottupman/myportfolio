package com.independentstudy.financeportfolio.asset;

import com.independentstudy.financeportfolio.trade.Trade;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class AssetService
{
    private final AssetRepository assetRepository;

    public List<Asset> getAssets(String username)
    {
        return assetRepository.findAssetsByUsername(username);
    }

    public Asset getAsset(String username, String symbol)
    {
        List<Asset> assets = assetRepository.findAssetByUsernameAndSymbol(username, symbol);
        if (assets.isEmpty())
            return null;
        return assets.get(0);
    }

    public void storeAsset(Asset asset)
    {
        assetRepository.save(asset);
    }

    public List<String> getSymbols(String username)
    {
        return assetRepository.findSymbolByUsername(username);
    }

    public double getQuantity(String username, String symbol) { return assetRepository.findQuantityByUsernameAndSymbol(username, symbol);}

    @Transactional
    public void modifyQuantity(Asset asset, double quantity)
    {
        asset.setQuantity(asset.getQuantity() + quantity);
        if (asset.getQuantity() == 0)
            deleteAsset(asset.getUsername(), asset.getSymbol());
    }

    public void deleteAsset(String username, String symbol)
    {
        assetRepository.deleteByUsernameAndSymbol(username, symbol);
    }
}
