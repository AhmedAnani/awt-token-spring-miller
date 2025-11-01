package com.Authsecurity.auth.repositry;

import com.Authsecurity.auth.model.UserModle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModle,Integer> {
    UserModle findByEmail(String email);

}
