package com.independentstudy.financeportfolio.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Integer>
{
    @Query(value = "SELECT symbol, SUM(value) AS profit\n" +
            "FROM (\n" +
            "    SELECT symbol, type, CASE\n" +
            "        WHEN type='Buy' THEN -SUM(price * quantity)\n" +
            "        WHEN type='Sell' THEN SUM(price * quantity)\n" +
            "    END AS value\n" +
            "    FROM trades\n" +
            "    WHERE username = ?1\n" +
            "    GROUP BY symbol, type\n" +
            ") sub\n" +
            "GROUP BY symbol;", nativeQuery = true)
    List<ProfitLossOnAssets> findProfitLossByUsername(String username);

    List<Trade> findTradesByUsername(String username);
}
