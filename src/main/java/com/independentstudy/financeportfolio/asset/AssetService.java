package com.independentstudy.financeportfolio.asset;

import lombok.Data;
import org.springframework.stereotype.Service;

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

    public List<String> getSymbols(String username)
    {
        return assetRepository.findSymbolByUsername(username);
    }
}
