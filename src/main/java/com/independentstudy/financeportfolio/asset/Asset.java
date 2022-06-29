package com.independentstudy.financeportfolio.asset;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

class AssetId implements Serializable
{
    private String username;
    private String symbol;
}
@Entity
@Table(name = "assets")
@Getter
@Setter
@ToString
@IdClass(AssetId.class)
public class Asset
{
    @Id private String username;
    @Id private String symbol;

    private String name;
    private String type;
    private Double quantity;

    public Asset()
    {

    }
    public Asset(String username, String symbol, String name, String type, Double quantity) {
        this.username = username;
        this.symbol = symbol;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
    }
}
