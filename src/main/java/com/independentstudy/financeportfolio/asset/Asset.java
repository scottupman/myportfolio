package com.independentstudy.financeportfolio.asset;

import lombok.Data;

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
@Data
@IdClass(AssetId.class)
public class Asset
{
    @Id String username;
    @Id String symbol;

    private String name;
    private String type;
    private Integer quantity;
}
