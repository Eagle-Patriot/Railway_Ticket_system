package com.mainproject.demo.Tripmate.Controller;

import com.mainproject.demo.Config.UserRole;
import com.mainproject.demo.RegistrationRequest;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Repository.TripmateRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private TripmateRepository tripmateRepository;
    private PasswordEncoder passwordEncoder;
    public Users register(RegistrationRequest request) {
        var user = new  Users(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                UserRole.USER
        );
       return tripmateRepository.save(user);
    }

}
