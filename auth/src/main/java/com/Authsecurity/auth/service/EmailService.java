package com.Authsecurity.auth.service;

import com.Authsecurity.auth.model.UserModle;
import com.Authsecurity.auth.repositry.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;


    public void sendOtp(String email,String otp){

                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(email);
                    message.setSubject("LMS OTP ");
                    message.setText("Your OTP is : el z3ym wa7d bs  GG " + otp + " Valid for ever");
                    javaMailSender.send(message);

    }

    public String generateOtp(){
        int otp = 100000 + new Random().nextInt(900000);
        return String.valueOf(otp);
    }
}
