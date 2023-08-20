package com.mainproject.demo.Tripmate.Repository;

import com.mainproject.demo.Tripmate.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenRepository extends
        JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String Token);

//@Transactional
//@Modifying
//@Query("UPDATE Token t " +
//        "SET t.confirmedAt = ?2 " +
//        "WHERE t.token = ?1")
//    int updateConfirmedAt(String token, LocalDateTime now);
}
