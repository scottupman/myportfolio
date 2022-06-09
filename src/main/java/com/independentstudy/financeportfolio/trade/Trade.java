package com.independentstudy.financeportfolio.trade;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Table(name = "trades")
@Data
public class Trade
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trades_seq_gen")
    @SequenceGenerator(name = "trades_seq_gen", sequenceName = "trades_id_gen")
    private Integer tradeId;

    private String symbol;
    private String name;
    private String type; // buy or sell
    private Integer quantity;
    private BigDecimal price;
    private ZonedDateTime dateOfTrade;
}
