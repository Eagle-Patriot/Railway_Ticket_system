package com.mainproject.demo.Tripmate.Controller;

import com.mainproject.demo.Events.RegistrationCompleteEvent;
import com.mainproject.demo.RegistrationRequest;
import com.mainproject.demo.Tripmate.Entity.Token;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Service.TokenService;
import com.mainproject.demo.Tripmate.UrlUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final ApplicationEventPublisher publisher;
    private final TokenService tokenService;


    @GetMapping("/register-form")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationRequest());
        return "register";
    }

    //    Description: Allows the user to register for a new account.
    @PostMapping("/registered")
    public String registernewUser(@ModelAttribute("user") RegistrationRequest request, HttpServletRequest requester) {
        Users user = registrationService.register(request);
        //publish the verification email event
        publisher.publishEvent(new RegistrationCompleteEvent(user, UrlUtil.getApplicationUrl(requester)));
        return "redirect:/register/register-form?success";
    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        Optional<Token> theToken = tokenService.getToken(token);
        if(theToken.isPresent() && theToken.get().getUsers().isEnabled()){
            return "redirect:/login?verified";
        }
        String verificationResult = tokenService.validateToken(token);
        return switch (verificationResult.toLowerCase()) {
            case "expired" -> "redirect:/error?expired";
            case "valid" -> "redirect:/login?valid";
            default -> "redirect:/error?invalid";
        };
    }
}
