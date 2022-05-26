package com.independentstudy.financeportfolio.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User
{
    @Id
    private String username;

    private String password;
    private String fname;
    private String lname;
    private String email;
    private LocalDate DOB;
}
