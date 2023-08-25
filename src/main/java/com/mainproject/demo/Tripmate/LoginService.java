package com.mainproject.demo.Tripmate;

import java.util.*;

import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Repository.TripmateRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService implements UserDetailsService {
    private final TripmateRepository tripmateRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return tripmateRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }
}
