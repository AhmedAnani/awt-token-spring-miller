package com.Authsecurity.auth.controller;

import com.Authsecurity.auth.api.authApi;
import com.Authsecurity.auth.model.UserModle;
import com.Authsecurity.auth.service.UserService;
import com.Authsecurity.auth.service.jwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController implements authApi {

     @Autowired
     private final AuthenticationManager authenticationManager;

     @Autowired
     private final jwtService jwtService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final UserService userService;


    public ResponseEntity<String> createToken( UserModle userModle ) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModle.getEmail(), userModle.getPassword())
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userModle.getEmail());
            final String jwt = jwtService.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user Not Found");
        }
    }

    public  ResponseEntity<String> check(){
        try{

            return ResponseEntity.ok("checked");
        } catch (Exception e) {
          return ResponseEntity.ok(  new RuntimeException(e).toString());
        }

    }

    public ResponseEntity<?> register( UserModle userModle){
         return   userService.register(userModle);
    }


    public ResponseEntity<?> confirmOtp( String email , String otp){
        return userService.verifyOtp(email,otp);
    }

}
