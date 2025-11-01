package com.Authsecurity.auth.service;

import com.Authsecurity.auth.model.UserModle;
import com.Authsecurity.auth.repositry.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    public final UserRepo userRepo;
    @Autowired
    public final EmailService emailService;

    public ResponseEntity<?> register(UserModle userModle){
       try {
           if (userRepo.findByEmail(userModle.getEmail()) != null)
               return ResponseEntity.status(HttpStatus.FOUND).body("Email Already Founded");

           String otp = emailService.generateOtp();
           userModle.setOtp(otp);
           userModle.setOtpExpiration(LocalDateTime.now().plusMinutes(5));
           userRepo.save(userModle);

           emailService.sendOtp(userModle.getEmail(), otp);
           return ResponseEntity.status(HttpStatus.CREATED).body("OTP Send to your Email Successfully");
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(e);
       }
    }

    public ResponseEntity<?> verifyOtp(String email,String otp){
        UserModle user = userRepo.findByEmail(email);
        try {


        if(user.getOtpExpiration().isBefore(LocalDateTime.now())) return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(" OTP Expired");

        if(!user.getOtp().equals(otp)) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(" OTP Wrong");

        if(user==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" User Not Found");

        user.setVerified(true);
        user.setOtp(null);
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account Verified Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(e);
        }
    }
}
