package com.mainproject.demo.Tripmate.Entity;

import com.mainproject.demo.Tripmate.ExpirationTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tokenId;

    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "userId"
    )
    private Users users;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date expiresAt;


    public Token(String token, Users users) {
        this.token = token;
        this.users = users;
        this.expiresAt = ExpirationTime.getExpirationTime();
    }
}
