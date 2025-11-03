package com.Authsecurity.auth.api;

import com.Authsecurity.auth.model.UserModle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface authApi {
    @PostMapping("/login")
     ResponseEntity<String> createToken(@RequestBody UserModle userModle );
    @GetMapping("/ch")
     ResponseEntity<String> check();
    @PostMapping("/register")
     ResponseEntity<?> register(@RequestBody UserModle userModle);
    @GetMapping("/otp")
     ResponseEntity<?> confirmOtp(@RequestBody UserModle userModle);
    @PostMapping("/reset")
      ResponseEntity<?> resetPassword(@RequestBody UserModle userModle);
}
