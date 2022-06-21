package com.independentstudy.financeportfolio.trade;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "trades")
@Getter @Setter
public class Trade
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer tradeId;

    private String username;
    private String symbol;
    private String name;
    private String type; // buy or sell
    private String securityType;
    private double quantity;
    private BigDecimal price;
    private Timestamp dateOfTrade;

    public Trade(String username, String symbol, String name, String type, String securityType, double quantity, BigDecimal price) {
        this.username = username;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.securityType = securityType;
        this.quantity = quantity;
        this.price = price;
        setDateOfTrade(new Timestamp(System.currentTimeMillis()));
    }
}
