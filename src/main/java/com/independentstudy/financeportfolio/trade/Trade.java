package com.independentstudy.financeportfolio.trade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "trades")
@Getter @Setter
@NoArgsConstructor
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
    private long timestamp;

    public Trade(String username, String symbol, String name, String type, String securityType, double quantity, BigDecimal price, long timestamp) {
        this.username = username;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.securityType = securityType;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }
}
