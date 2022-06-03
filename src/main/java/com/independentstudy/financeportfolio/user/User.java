package com.independentstudy.financeportfolio.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User
{
    @Id
    private String username;

    private String password;
    private String fname;
    private String lname;
    private String email;
    private LocalDate DOB;
    private BigDecimal cashValue;

    public User(String username, String password, String fname, String lname, String email, LocalDate DOB) {
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.DOB = DOB;
        setCashValue(new BigDecimal("0.00"));
    }
}
