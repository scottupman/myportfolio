package com.independentstudy.financeportfolio.asset;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("assets")
public class AssetController
{
    private final AssetService assetService;

    @GetMapping("{username}")
    public ResponseEntity getAssets(@PathVariable String username)
    {
        List<Asset> myAssets = assetService.getAssets(username);
        return ResponseEntity.ok(myAssets);
    }

    @GetMapping("{username}/symbols")
    public ResponseEntity getSymbols(@PathVariable String username)
    {
        List<String> symbols = assetService.getSymbols(username);
        return ResponseEntity.ok(symbols);
    }
}
