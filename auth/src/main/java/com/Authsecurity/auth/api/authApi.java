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
    @PostMapping("/otp")
    public ResponseEntity<?> confirmOtp(@RequestParam String email , @RequestParam String otp);
}
