package com.kouiz.demo.service;

import com.kouiz.demo.entity.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken findByToken(String token);

    public RefreshToken createRefreshToken(int userId);

    public RefreshToken verifyExpiration(RefreshToken token);

    public int deleteByUserId(int userId);
}
