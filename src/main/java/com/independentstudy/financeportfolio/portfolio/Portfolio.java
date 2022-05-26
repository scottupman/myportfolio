package com.independentstudy.financeportfolio.portfolio;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "portfolio")
@Getter @Setter @ToString
public class Portfolio
{
    @Id
    @SequenceGenerator(name = "seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private Integer id;

    private String username;
    private BigDecimal cashValue;
    private Integer numberOfPositions;

    public Portfolio(){

    }
    public Portfolio(String username) {
        this.username = username;
        this.cashValue = new BigDecimal("0.00");
        this.numberOfPositions = 0;

    }

}
