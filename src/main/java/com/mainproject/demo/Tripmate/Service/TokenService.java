package com.mainproject.demo.Tripmate.Service;

import com.mainproject.demo.Tripmate.Entity.Token;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Repository.TokenRepository;
import com.mainproject.demo.Tripmate.Repository.TripmateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final TripmateRepository tripmateRepository;

    public String validateToken(String token){
        Optional<Token> theToken = tokenRepository.findByToken(token);
        if (theToken.isEmpty()){
            return "INVALID";
        }
        Users user = theToken.get().getUsers();
        Calendar calendar = Calendar.getInstance();
        if ((theToken.get().getExpiresAt()
                .getTime() - calendar.getTime().getTime()) <= 0){
            return "EXPIRED";
        }
        user.setEnabled(true);
        tripmateRepository.save(user);
        return "VALID";
    }

    public void saveToken(Users users, String token) {
        var token1 = new Token(token, users);
        tokenRepository.save(token1);
    }

    public Optional<Token> getToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
