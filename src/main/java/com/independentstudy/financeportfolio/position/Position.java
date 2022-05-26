package com.independentstudy.financeportfolio.position;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "positions")
@Getter
@Setter
@ToString
public class Position
{
    private Integer id;
    private Integer portfolioID;
    private String name;
    private String symbol;
    private BigDecimal currentPrice;
    private BigDecimal priceBought;
    private Integer quantity;
    private BigDecimal profitLoss;
    private boolean isCrypto;

    public Position(Integer portfolioID, String name, String symbol, BigDecimal currentPrice, BigDecimal priceBought, Integer quantity, BigDecimal profitLoss, boolean isCrypto) {
        this.portfolioID = portfolioID;
        this.name = name;
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.priceBought = priceBought;
        this.quantity = quantity;
        this.profitLoss = profitLoss;
        this.isCrypto = isCrypto;
    }
}
