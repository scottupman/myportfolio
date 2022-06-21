package com.independentstudy.financeportfolio.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String>
{
    @Query(value = "SELECT * FROM assets WHERE username = ?1", nativeQuery = true)
    List<Asset> findAssetsByUsername(String username);

    @Query(value = "SELECT symbol FROM assets WHERE username = ?1", nativeQuery = true)
    List<String> findSymbolByUsername(String username);

    @Query(value = "SELECT quantity FROM assets WHERE username = ?1 AND symbol = ?2", nativeQuery = true)
    double findQuantityByUsernameAndSymbol(String username, String symbol);

    List<Asset> findAssetByUsernameAndSymbol(String username, String symbol);

    void deleteByUsernameAndSymbol(String username, String symbol);
}
