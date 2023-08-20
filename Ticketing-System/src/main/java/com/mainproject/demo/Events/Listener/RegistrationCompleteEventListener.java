package com.mainproject.demo.Events.Listener;

import com.mainproject.demo.Events.RegistrationCompleteEvent;
import com.mainproject.demo.Tripmate.Entity.Users;
import com.mainproject.demo.Tripmate.Service.TokenService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {
    private final TokenService tokenService;
    private final JavaMailSender mailSender;
    private Users users;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //get the user
        users = event.getUser();
        //generate token for user
        String VToken = UUID.randomUUID().toString();
        //save the token for user
        tokenService.saveToken(users,VToken);
        //Build verification url
        String url = event.getConfirmationUrl()+"/register/verifyEmail?token="+VToken;
        //send the email to the user
        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


    }

    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Verification Service";
        String mailContent = "<p> Hi, "+ users.getFirstName()+ ", </p>"+
                "<p>Thank you for registering with us."+" " +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\""  +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Verification Service";
        emailMessage(subject,senderName,mailContent,mailSender,users);
    }

    private void emailMessage(String subject,
                              String senderName,
                              String mailContent,
                              JavaMailSender mailSender,
                              Users users) throws MessagingException,UnsupportedEncodingException{
           MimeMessage message = mailSender.createMimeMessage();
           var messageHelper = new MimeMessageHelper(message);
            messageHelper.setText(mailContent, true);
            messageHelper.setTo(users.getEmail());
            messageHelper.setSubject(subject);
            messageHelper.setFrom("c89lash@gmail.com",senderName);
            mailSender.send(message);
    }
}
