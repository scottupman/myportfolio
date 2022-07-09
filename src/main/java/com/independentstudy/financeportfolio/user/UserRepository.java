package com.independentstudy.financeportfolio.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    List<User> findByEmailIgnoreCase(String email);

    @Query(value = "SELECT cash_value FROM users WHERE username = ?1", nativeQuery = true)
    BigDecimal findCashValueByUsername(String username);

    @Query(value = "SELECT username FROM users", nativeQuery = true)
    List<String> getAllUsernames();

    @Query(value = "SELECT email FROM users", nativeQuery = true)
    List<String> getAllEmails();
}
