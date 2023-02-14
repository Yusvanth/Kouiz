package com.kouiz.demo.repository;

import com.kouiz.demo.entity.RefreshToken;
import com.kouiz.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer> {
    RefreshToken findByToken(String token);

    int deleteByUser(User byId);
}
